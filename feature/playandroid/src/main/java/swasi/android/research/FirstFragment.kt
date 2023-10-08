package swasi.android.research

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import swasi.android.research.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            val list = mutableListOf<Person>()
            for (i in 1..5) {
                val childView = _binding?.llContainer?.getChildAt(i - 1)
                childView?.let {
                    val person = verifyPersonData(it, i)
                    list.add(person)
                }
            }

            if (list.isEmpty()) {

            } else {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        }
        for (i in 1..5) {
            addPersonForm(i)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addPersonForm(position: Int) {
        val formLayout: View =
            LayoutInflater.from(requireActivity())
                .inflate(R.layout.form_layout, _binding?.llContainer, false)
        val textView = formLayout.findViewById<View>(R.id.tvPerson) as AppCompatTextView
        val etFirstName = formLayout.findViewById<View>(R.id.etFirstName) as AppCompatEditText
        val etLastName = formLayout.findViewById<View>(R.id.etLastName) as AppCompatEditText
        textView.text = "Person $position"
        etFirstName.hint = "Enter Person $position First Name"
        etLastName.hint = "Enter Person $position Last Name"
        _binding?.llContainer?.addView(formLayout)
    }

    private fun verifyPersonData(formLayout: View, position: Int): Person {
        val textView = formLayout.findViewById<View>(R.id.tvPerson) as AppCompatTextView
        val etFirstName = formLayout.findViewById<View>(R.id.etFirstName) as AppCompatEditText
        val etLastName = formLayout.findViewById<View>(R.id.etLastName) as AppCompatEditText
        val fName = etFirstName.text.toString()
        val lName = etLastName.text.toString()

        if (fName.isEmpty()) {
            etFirstName.error = "Please Enter Person $position First Name"
        }

        if (lName.isEmpty()) {
            etFirstName.error = "Please Enter Person $position Last Name"
        }
        return Person(position, fName, lName)
    }

}

data class Person(val id: Int, val fName: String, val lName: String)