package ru.nsu.malov.lab9.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import ru.nsu.malov.lab9.R
import ru.nsu.malov.lab9.databinding.DialogCodeBinding
import ru.nsu.malov.lab9.log_tags.LogTags
import ru.nsu.malov.lab9.network.StatusValue
import ru.nsu.malov.lab9.view_models.SendMoneyViewModel

class DialogCodeFragment(private val sendMoneyViewModel: SendMoneyViewModel) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_code, null)
        val enterCode = dialogLayout.findViewById<EditText>(R.id.code)
        return AlertDialog.Builder(requireContext()).setTitle(resources.getString(R.string.enter_code)).setPositiveButton("OK"){_, _ ->
            run {
                Log.d(LogTags.sendMoney, enterCode.text.toString())
                sendMoneyViewModel.sendCode(enterCode.text.toString().toInt())
            }
        }.setView(dialogLayout).create()
    }
}