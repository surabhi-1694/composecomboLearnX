package com.example.ecomapp.signup

import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

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
}