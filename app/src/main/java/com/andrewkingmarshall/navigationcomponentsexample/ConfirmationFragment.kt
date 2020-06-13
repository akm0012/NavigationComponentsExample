package com.andrewkingmarshall.navigationcomponentsexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_confirmation.*
import java.math.BigDecimal

class ConfirmationFragment : Fragment() {

    lateinit var navController: NavController

    lateinit var recipient: String
    lateinit var amount: Money


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recipient = arguments?.getString("recipient") ?: ""
        amount = arguments?.getParcelable("amount") ?: Money(BigDecimal.ZERO)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        confirmation_message.text = "$${amount.amount} was sent to $recipient!"

        add_another_button.setOnClickListener {
            navController.navigate(R.id.action_confirmationFragment_to_chooseRecipientFragment)
        }
    }

}
