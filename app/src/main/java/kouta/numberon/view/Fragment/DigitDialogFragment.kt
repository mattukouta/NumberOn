package kouta.numberon.view.Fragment

import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

import kouta.numberon.R


class DigitDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState : Bundle?) : Dialog {
        super.onCreateDialog(savedInstanceState)

        val alert = activity?.let { AlertDialog.Builder(it) }
        val view = activity?.layoutInflater?.inflate(R.layout.fragment_digit_dialog, null)

        alert?.setView(view)

        return alert?.create()!!
    }
}
