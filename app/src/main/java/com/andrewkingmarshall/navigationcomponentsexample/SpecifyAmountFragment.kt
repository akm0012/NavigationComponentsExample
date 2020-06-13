package com.andrewkingmarshall.navigationcomponentsexample

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_specify_amount.*
import java.math.BigDecimal

class SpecifyAmountFragment : Fragment() {

    lateinit var navController: NavController

    lateinit var recipient: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recipient = arguments?.getString("recipient") ?: "Not the person you are looking for."

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specify_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipient_textView.text = "Send money to: $recipient"

        navController = Navigation.findNavController(view)

        send_btn.setOnClickListener {

            if (!TextUtils.isEmpty(input_amount.text.toString())) {

                val money = Money(BigDecimal(input_amount.text.toString()))

                val bundle = bundleOf(
                    "amount" to money,
                    "recipient" to recipient
                )

                navController.navigate(
                    R.id.action_specifyAmountFragment_to_confirmationFragment,
                    bundle
                )

            } else {
                input_amount.error = "You must enter some amount."
            }
        }

        cancel_btn.setOnClickListener {
            navController.popBackStack()
        }
    }

}
