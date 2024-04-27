package com.example.countercompoundview

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class UpdateCounterDialogFragment(val onClickTap:() -> Unit): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.title)
            .setPositiveButton(getString(R.string.positive)) { _,_ -> onClickTap.invoke() }
            .setNegativeButton(R.string.negative){_,_->}
            .create()
}