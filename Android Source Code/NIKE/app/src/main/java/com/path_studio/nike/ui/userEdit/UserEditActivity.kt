package com.path_studio.nike.ui.userEdit

import android.app.DatePickerDialog
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.path_studio.nike.R
import com.path_studio.nike.data.source.local.entity.UserEntity
import com.path_studio.nike.databinding.ActivityMainBinding
import com.path_studio.nike.databinding.ActivityUserEditBinding
import com.path_studio.nike.ui.main.userAccount.UserViewModel
import com.path_studio.nike.viewModel.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class UserEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserEditBinding

    private lateinit var prefs: SharedPreferences
    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var dateFormatter: SimpleDateFormat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = getSharedPreferences("com.path_studio.nike", AppCompatActivity.MODE_PRIVATE)

        //set field to current user data
        setUserDataInFields()

        //date picker
        binding.btnEditBirthday.setOnClickListener{
            showDateDialog()
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.editEditBtn.setOnClickListener {
            showLoadingIndicator(true)

            val factory = ViewModelFactory.getInstance(this)
            val viewModel = ViewModelProvider(this, factory)[UserEditViewModel::class.java]

            with(binding) {
                val name = editNameField.text.toString()
                val email = editEmailField.text.toString()
                val password = editPasswordField.text.toString()
                val phoneNumber = editPhoneNumberField.text.toString()
                val address = editAddressField.text.toString()
                val birthday = editBirthdayField.text.toString()

                if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && phoneNumber.isNotEmpty()
                    && birthday.isNotEmpty() && address.isNotEmpty()){
                    val userData = UserEntity(
                        null,
                        name,
                        email,
                        password,
                        phoneNumber,
                        address,
                        birthday,
                        1
                        )

                    viewModel.updateUserData(userData).observe(this@UserEditActivity, { status ->
                        when(status){
                            "success" ->{
                                showLoadingIndicator(false)

                                prefs.edit().putString("userName", name).apply()
                                prefs.edit().putString("userEmail", email).apply()
                                prefs.edit().putString("userAddress", address).apply()
                                prefs.edit().putString("userPhoneNumber", phoneNumber).apply()
                                prefs.edit().putString("userBirthDay", birthday).apply()

                                Toast.makeText(
                                    this@UserEditActivity,
                                    "Success to Update",
                                    Toast.LENGTH_LONG
                                ).show()

                                //back to user Account page
                                onBackPressed()
                            }
                            "failed" ->{
                                showLoadingIndicator(false)
                                Toast.makeText(
                                    this@UserEditActivity,
                                    "Failed to Update",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    })
                }else{
                    showLoadingIndicator(false)
                    Toast.makeText(
                        this@UserEditActivity,
                        "Please Fill All Data",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun showLoadingIndicator(isLoading: Boolean){
        with(binding) {
            progressBar.isVisible = isLoading
            editTextContainer.isVisible = !isLoading
        }
    }

    private fun showDateDialog() {
        val newCalendar: Calendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->
            val newDate: Calendar = Calendar.getInstance()
            newDate.set(year, monthOfYear, dayOfMonth)
            binding.editBirthdayField.setText(dateFormatter.format(newDate.time))
        },
            newCalendar.get(Calendar.YEAR),
            newCalendar.get(Calendar.MONTH),
            newCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun setUserDataInFields(){
        if(prefs.getBoolean("isLogin", false)){
            with(binding){
                editNameField.setText(prefs.getString("userName", "").toString().trim())
                editEmailField.setText(prefs.getString("userEmail", "").toString().trim())
                editAddressField.setText(prefs.getString("userAddress", "").toString().trim())
                editPhoneNumberField.setText(prefs.getString("userPhoneNumber", "").toString().trim())
                editBirthdayField.setText(prefs.getString("userBirthDay", "").toString().trim())
            }
        }
    }
}