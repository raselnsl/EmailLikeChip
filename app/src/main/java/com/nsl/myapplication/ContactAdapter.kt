package com.nsl.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter


class ContactAdapter(
    context: Context,
    private val viewResourceId: Int,
    private val items: ArrayList<Contact>

) : ArrayAdapter<Contact>(context, viewResourceId, items) {


    lateinit var itemsAll: ArrayList<Contact>
    lateinit var suggestions: ArrayList<Contact>

    internal var nameFilter: Filter = object : Filter() {

        override fun convertResultToString(resultValue: Any): String {
            return (resultValue as Contact).getName()
        }

        @SuppressLint("DefaultLocale")
        protected override fun performFiltering(constraint: CharSequence?): FilterResults {
            if (constraint != null) {
                suggestions.clear()
                for (customer in itemsAll) {
                    if (customer.getName().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(customer)
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = suggestions
                filterResults.count = suggestions.size
                return filterResults
            } else {
                return FilterResults()
            }
        }

        protected override fun publishResults(constraint: CharSequence, results: FilterResults?) {
            val filteredList = results!!.values as ArrayList<Contact>
            if (results.count > 0) {
                clear()
                for (c in filteredList) {
                    add(c)
                }
                notifyDataSetChanged()
            }
        }
    }

    init {
        this.itemsAll = items.clone() as ArrayList<Contact>
        this.suggestions = ArrayList<Contact>()
    }

   /* fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        var v: View? = convertView
        if (v == null) {
            val vi = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            v = vi.inflate(viewResourceId, null)
        }
        val customer = items[position]
        val customerNameLabel = v!!.findViewById(R.id.customerNameLabel) as TextView
        customerNameLabel.setText(customer.getName())
        return v
    }  */

    override fun getFilter(): Filter {
        return nameFilter
    }

}