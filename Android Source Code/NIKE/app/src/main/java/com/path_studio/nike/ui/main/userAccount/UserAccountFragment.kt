package com.path_studio.nike.ui.main.userAccount

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.path_studio.nike.R
import com.path_studio.nike.data.source.local.entity.UserEntity
import com.path_studio.nike.databinding.FragmentUserAccountBinding

import android.app.DatePickerDialog
import android.content.SharedPreferences
import java.util.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.path_studio.nike.viewModel.ViewModelFactory
import java.text.SimpleDateFormat


class UserAccountFragment : Fragment() {
    private var _binding: FragmentUserAccountBinding? = null
    private val binding get() = _binding as FragmentUserAccountBinding

    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var dateFormatter:SimpleDateFormat
    private lateinit var prefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //set binding
        _binding = FragmentUserAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(requireActivity(), factory)[UserViewModel::class.java]
        prefs = requireActivity().getSharedPreferences("com.path_studio.nike", AppCompatActivity.MODE_PRIVATE)

        if(!prefs.getBoolean("isLogin", false) && prefs.getString("userName", "")?.isEmpty() == true) {
            setLayoutVisibility("login")
        }else{
            setLayoutVisibility("afterLogin")
            setUserData()
        }

        //scroll listener
        binding.scrollContainer.viewTreeObserver.addOnScrollChangedListener {
            val scrollY: Int = binding.scrollContainer.scrollY
            val navView = activity?.findViewById<View>(R.id.top_nav_container)

            if (scrollY in 0..70) {
                navView?.visibility = View.VISIBLE
            }else{
                navView?.visibility = View.INVISIBLE
            }
        }

        //date picker
        binding.btnSignUpBirthday.setOnClickListener{
            showDateDialog()
        }

        binding.signUpSignUpBtn.setOnClickListener {
            showLoadingIndicator(true)
            setLayoutVisibility("no")

            //insert new user
            val name = binding.signUpNameField.text.toString()
            val email = binding.signUpEmailField.text.toString()
            val password = binding.signUpPasswordField.text.toString()
            val phoneNumber = binding.signUpPhoneNumberField.text.toString()
            val address = binding.signUpAddressField.text.toString()
            val birthDay = binding.signUpBirthdayField.text.toString()

            if(name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && phoneNumber.isNotEmpty() &&
                address.isNotEmpty() && birthDay.isNotEmpty()) {
                val newUser = UserEntity(
                    null,
                    name,
                    email,
                    password,
                    phoneNumber,
                    address,
                    birthDay,
                    null
                )
                viewModel.insertUserData(newUser).observe(requireActivity(), { status ->
                    when {
                        status.equals("success", ignoreCase = true) -> {
                            Toast.makeText(
                                requireActivity(),
                                "Success to Sign Up",
                                Toast.LENGTH_LONG
                            ).show()
                            setLayoutVisibility("login")
                        }
                        status.equals("already_register", ignoreCase = true) -> {
                            Toast.makeText(
                                requireActivity(),
                                "Already Register",
                                Toast.LENGTH_LONG
                            ).show()
                            setLayoutVisibility("signUp")
                        }
                        status.equals("failed", ignoreCase = true) -> {
                            Toast.makeText(
                                requireActivity(),
                                "Failed to Sign Up",
                                Toast.LENGTH_LONG
                            ).show()
                            setLayoutVisibility("signUp")
                        }
                    }
                    showLoadingIndicator(false)
                })
            }else{
                Toast.makeText(
                    requireActivity(),
                    "Please Fill All Fields",
                    Toast.LENGTH_SHORT
                ).show()
                showLoadingIndicator(false)
            }
        }

        binding.loginLoginBtn.setOnClickListener {
            showLoadingIndicator(true)
            setLayoutVisibility("no")

            //update user status to login
            val email = binding.loginEmailField.text.toString()
            val password = binding.loginPasswordField.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                viewModel.updateUserLogin(email, password, 1).observe(requireActivity(), { status ->
                    when(status){
                        "failed_still_login" ->{
                            Toast.makeText(
                                requireActivity(),
                                "Still Login",
                                Toast.LENGTH_LONG
                            ).show()
                            setLayoutVisibility("login")
                        }
                        "success" ->{
                            //get userData
                            viewModel.getUserData(email).observe(requireActivity(), { data ->
                                if(data != null){
                                    //save user information and status login in SharedPreferences
                                    if(!prefs.getBoolean("isLogin", false)){
                                        prefs.edit().putBoolean("isLogin", true).apply()
                                    }

                                    prefs.edit().putString("userName", data.name).apply()
                                    prefs.edit().putString("userEmail", data.email).apply()
                                    prefs.edit().putString("userAddress", data.address).apply()
                                    prefs.edit().putString("userPhoneNumber", data.phone_number).apply()
                                    prefs.edit().putString("userBirthDay", data.birthday).apply()

                                    Toast.makeText(
                                        requireActivity(),
                                        "Success to Login",
                                        Toast.LENGTH_LONG
                                    ).show()

                                    //set the user data into after login container
                                    setUserData()
                                    setLayoutVisibility("afterLogin")
                                }
                            })
                        }
                        "failed_update_data" ->{
                            Toast.makeText(
                                requireActivity(),
                                "Failed to Login",
                                Toast.LENGTH_LONG
                            ).show()
                            setLayoutVisibility("login")
                        }
                        "email_or_password_wrong" ->{
                            Toast.makeText(
                                requireActivity(),
                                "Wrong Email or Password",
                                Toast.LENGTH_LONG
                            ).show()
                            setLayoutVisibility("login")
                        }
                        "email_or_password_empty" ->{
                            Toast.makeText(
                                requireActivity(),
                                "Email or Password Still Empty",
                                Toast.LENGTH_LONG
                            ).show()
                            setLayoutVisibility("login")
                        }
                    }
                    showLoadingIndicator(false)
                })
            }else{
                Toast.makeText(
                    requireActivity(),
                    "Please Fill All Fields",
                    Toast.LENGTH_SHORT
                ).show()
                setLayoutVisibility("login")
                showLoadingIndicator(false)
            }
        }

        binding.userBtnLogout.setOnClickListener {
            showLoadingIndicator(true)
            setLayoutVisibility("no")

            //update user status to login
            if(prefs.getBoolean("isLogin", false)) {
                val email = prefs.getString("userEmail", "").toString()

                if (email.isNotEmpty()) {
                    viewModel.updateUserLogin(email, 0)
                        .observe(requireActivity(), { status ->
                            when (status) {
                                "success" -> {
                                    prefs.edit().putBoolean("isLogin", false).apply()

                                    //remove user data in sharepreferences
                                    prefs.edit().remove("userName").apply()
                                    prefs.edit().remove("userEmail").apply()
                                    prefs.edit().remove("userAddress").apply()
                                    prefs.edit().remove("userPhoneNumber").apply()
                                    prefs.edit().remove("userBirthDay").apply()

                                    //change person icon to available
                                    binding.userProfile.setImageDrawable(requireActivity().getDrawable(R.drawable.not_registered))

                                    Toast.makeText(
                                        requireActivity(),
                                        "Success to Logout",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    setLayoutVisibility("login")
                                }
                                "failed" -> {
                                    Toast.makeText(
                                        requireActivity(),
                                        "Failed to Logout",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    setLayoutVisibility("afterLogin")
                                }
                            }
                            showLoadingIndicator(false)
                        })
                } else {
                    Toast.makeText(
                        requireActivity(),
                        "Please Fill All Fields",
                        Toast.LENGTH_SHORT
                    ).show()
                    setLayoutVisibility("login")
                    showLoadingIndicator(false)
                }
            }
        }

        binding.signUpLoginBtn.setOnClickListener {
            setLayoutVisibility("login")
        }

        binding.loginSignUpBtn.setOnClickListener {
            setLayoutVisibility("signUp")
        }
    }

    private fun showLoadingIndicator(status: Boolean){
        binding.progressBar.isVisible = status
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setUserData(){
        if(prefs.getBoolean("isLogin", false)){
            with(binding){
                //change person icon to available
                userProfile.setImageDrawable(requireActivity().getDrawable(R.drawable.user_icon))

                userName.text = prefs.getString("userName", "").toString()
                userEmail.text = prefs.getString("userEmail", "").toString()
                userAddress.text = prefs.getString("userAddress", "").toString()
                userPhoneNumber.text = prefs.getString("userPhoneNumber", "").toString()
            }
        }
    }

    private fun showDateDialog() {
        val newCalendar: Calendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(requireActivity(), { view, year, monthOfYear, dayOfMonth ->
                val newDate: Calendar = Calendar.getInstance()
                newDate.set(year, monthOfYear, dayOfMonth)
                binding.signUpBirthdayField.setText(dateFormatter.format(newDate.time))
            },
            newCalendar.get(Calendar.YEAR),
            newCalendar.get(Calendar.MONTH),
            newCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun setLayoutVisibility(status: String){
        with(binding) {
            when (status) {
                "signUp" -> {
                    signUpContainer.isVisible = true
                    loginContainer.isVisible = false
                    afterLoginContainerSkeleton.isVisible = false
                }
                "login" -> {
                    signUpContainer.isVisible = false
                    loginContainer.isVisible = true
                    afterLoginContainerSkeleton.isVisible = false
                }
                "afterLogin" -> {
                    signUpContainer.isVisible = false
                    loginContainer.isVisible = false
                    afterLoginContainerSkeleton.isVisible = true
                }
                "no" -> {
                    signUpContainer.isVisible = false
                    loginContainer.isVisible = false
                    afterLoginContainerSkeleton.isVisible = false
                }
            }
        }
    }
}