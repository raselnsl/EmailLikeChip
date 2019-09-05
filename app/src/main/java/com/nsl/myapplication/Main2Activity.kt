package com.nsl.myapplication

import android.os.Bundle
import android.text.Spanned
import android.text.style.ImageSpan
import android.widget.ArrayAdapter
import android.widget.MultiAutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.chip.ChipDrawable


class Main2Activity : AppCompatActivity() {

    lateinit var contactAutoCompleteTextView : MultiAutoCompleteTextView
    private val PEOPLE = arrayOf("John Smith", "Kate Eckhart", "Emily Sun", "Frodo Baggins")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

       /* buttonAddChip.setOnClickListener {
            // performFileSearch()

            val text = etTextChip.getText()

            // Inflate from resources.
            val chip = ChipDrawable.createFromResource(it.context, R.xml.standalone_chip)
            chip.setText(text)

            // Use it as a Drawable however you want
            chip.setBounds(0, 0, chip.intrinsicWidth, chip.intrinsicHeight)
            val span = ImageSpan(chip)
            text?.setSpan(span, 0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            etTextChip.setSelection()
        }*/

        contactAutoCompleteTextView = findViewById(R.id.recipient_auto_complete_text_view) as MultiAutoCompleteTextView
        val contacts = object : ArrayList<Contact>() {
            init {
                add(Contact("Adam Ford", R.drawable.ic_person))
                add(Contact("Adele McCormick", R.drawable.ic_person))
                add(Contact("Alexandra Hollander", R.drawable.ic_person))
                add(Contact("Alice Paul", R.drawable.ic_person))
                add(Contact("Arthur Roch", R.drawable.ic_person))
            }
        }

       /* contactAutoCompleteTextView.setAdapter(ContactAdapter(this, R.layout.contact_layout, contacts))

        contactAutoCompleteTextView.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
        // Minimum number of characters the user has to type before the drop-down list is shown
        contactAutoCompleteTextView.setThreshold(1)
        contactAutoCompleteTextView.setOnItemClickListener(object :
            AdapterView.OnItemClickListener {
            override fun onItemClick(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                val selectedContact = adapterView.getItemAtPosition(i) as Contact
                createRecipientChip(selectedContact)
            }
        })  */

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, PEOPLE)
        contactAutoCompleteTextView.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
        // Minimum number of characters the user has to type before the drop-down list is shown
        contactAutoCompleteTextView.setThreshold(1)
        contactAutoCompleteTextView.setAdapter<ArrayAdapter<String>>(adapter)
        contactAutoCompleteTextView.setOnItemClickListener { parent, arg1, position, arg3 ->
            val selected = parent.getItemAtPosition(position) as String
            createRecipientChip(selected)
        }

    }

    private fun createRecipientChip(selectedContact: String) {
        val chip = ChipDrawable.createFromResource(this, R.xml.standalone_chip)
       // val span = CenteredImageSpan(chip, 40f, 40f)
        val span = ImageSpan(chip)
        val cursorPosition = contactAutoCompleteTextView.getSelectionStart()
        val spanLength = selectedContact.length + 2
        val text = contactAutoCompleteTextView.getText()
        chip.chipIcon = ContextCompat.getDrawable(this@Main2Activity, R.drawable.ic_person)
        chip.setText(selectedContact)
        chip.setBounds(0, 0, chip.intrinsicWidth, chip.intrinsicHeight)


        text.setSpan(
            span,
            cursorPosition - spanLength,
            cursorPosition,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}
