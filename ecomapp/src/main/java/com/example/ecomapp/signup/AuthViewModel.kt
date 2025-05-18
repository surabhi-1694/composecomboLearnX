package com.example.ecomapp.signup

import androidx.lifecycle.ViewModel
import com.example.ecomapp.Home.CategoryData
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class AuthViewModel:ViewModel() {

    private var auth = Firebase.auth

    private var firestore = Firebase.firestore

    //pass user enter email ,pwd to provide it to firestore
    //firestore return succesfull or failure ; so we will return boolean to confirm success or failure
    //also we will pass strign msg in case of failure to display msg
    fun signup(email:String,name:String,password:String,onResult:(Boolean,String?)->Unit)
    {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{ userresult ->
            if(userresult.isSuccessful){
                /**
                 * here we are creating user from app so we called create use method
                 * firestore has collection (we can consider it as table) and  document(we can consider as data )
                 * now here we add document to user collection .
                 * now on each user creation we need new document so that we need unique id to differntiate unique data
                 *
                 * here we create data class for user in order to pass it in document
                 *
                * */

                val user =
                    User(name = name,email=email,uid = userresult.result?.user?.uid!!)
                firestore.collection("Users").document(user.uid)
                    .set(user)
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            onResult(true,null)
                        }else{
                            onResult(false,"Something went wrong")
                        }
                    }
            }else{
                onResult(false, userresult.exception?.localizedMessage.toString())
            }
        }
    }

    //Login with firestore
    fun login(email: String,password: String,onResult: (Task<AuthResult>?,Boolean, String?) -> Unit){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
            if(it.isSuccessful){
                onResult(it,true,null)
            }else{
                onResult(null,false,it.exception?.localizedMessage)
            }
        }
    }

    //addOnCompleteListener can not return directly like this as it has to wait to get result
   // instead wait for result so we can either use suspend , await or callback to get values
    // here in login fun. we already try callback so now let's try await() to wait for result
    suspend fun getUserName(): String? {

//
//        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return null
//        val snapshot = Firebase.firestore.collection("Users")
//            .document(uid)
//            .get()
//            .await()
//
//        return snapshot.getString("name")

        val uId = FirebaseAuth.getInstance().currentUser?.uid?:return null
        val snapshot = Firebase.firestore.collection("Users")
            .document(uId)
            .get()
            .await()
        //this will await to get data of document and then we get name from it
        return snapshot.getString("name")
    }


    //we can get Banner list from collection in following both ways
    // it is important to await for result and then move to ui other wise will have an empty value
    suspend fun getBannerURL(): List<String> {
        val bannerList = Firebase.firestore.collection("data")
            .document("banners")
            .get()
            .await()
        return bannerList.get("ImageUrls") as List<String>
    }

    fun getBanners(): List<String> {
        var bannerList:List<String> = emptyList()
        Firebase.firestore.collection("data")
            .document("banners")
            .get().addOnCompleteListener {
                bannerList = it.result.get("ImageUrls") as List<String>
        }
        return bannerList
    }

    fun getCategoriesList():List<CategoryData>{
        var categoryList:List<CategoryData> = emptyList()
        Firebase.firestore.collection("data")
            .document("stock")
            .collection("categories")
            .get().addOnCompleteListener {
                if(it.isSuccessful){
                    categoryList = it.result.documents.mapNotNull { doc->
                        doc.toObject(CategoryData::class.java)
                    }
                }
            }
        return categoryList
    }

   suspend fun getCategories(): MutableList<DocumentSnapshot> {
       val categoriesList = Firebase.firestore.collection("data")
           .document("stock").collection("categories")
           .get()
           .await()
       return categoriesList.documents


    }
}