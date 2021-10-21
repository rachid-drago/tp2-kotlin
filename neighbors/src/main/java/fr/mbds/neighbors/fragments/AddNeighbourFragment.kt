package fr.mbds.neighbors.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isNotEmpty
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import fr.mbds.neighbors.NavigationListener
import fr.mbds.neighbors.R
import fr.mbds.neighbors.data.NeighborRepository
import fr.mbds.neighbors.models.Neighbor

class AddNeighbourFragment : Fragment() {
    private lateinit var valider: Button
    private lateinit var image: TextInputLayout
    private lateinit var nom: TextInputLayout
    private lateinit var phone: TextInputLayout
    private lateinit var website: TextInputLayout
    private lateinit var adress: TextInputLayout
    private lateinit var aboutme: TextInputLayout
    private lateinit var back: FloatingActionButton
    var check = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.add_neighbor, container, false)
        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.title2)
        }

        val id = 1 + countNeighbour()
        nom = view.findViewById(R.id.textFieldNom)
        image = view.findViewById(R.id.textFieldImage)
        phone = view.findViewById(R.id.textFieldTelephone)
        website = view.findViewById(R.id.textFieldWebsite)
        aboutme = view.findViewById(R.id.textFieldApropos)
        adress = view.findViewById(R.id.textFieldAdresse)
        back = view.findViewById(R.id.backbutton)
        valider = view.findViewById(R.id.buttonAdd)

        this.control()
         valider.isEnabled = false
        valider.setOnClickListener {

            val new = Neighbor(
                id.toLong(),
                nom?.getEditText()?.getText().toString(),
                image?.getEditText()?.getText().toString(),
                adress.editText?.text.toString(),
                phone.editText?.text.toString(),
                aboutme.editText?.text.toString(),
                false,
                website.editText?.text.toString()
            )
            println(
                " name = " + nom.editText?.text.toString() + "  image : " +
                    image.editText?.text.toString()
            )

            addNeighbour(new)
            Toast.makeText(context, "Neighbor ${nom.editText?.text} was added successfully", Toast.LENGTH_LONG).show()
            (activity as? NavigationListener)?.let {
                it.showFragment1(ListNeighborsFragment())
            }
        }

        back.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment1(ListNeighborsFragment())
            }
        }

        return view
    }

    private fun control() {
        this.controlImage()
        this.controlName()
        this.controlPhone()
        this.controlWebsite()
        this.controlAdress()
        this.controlAboutMe()
        if (nom.isNotEmpty())
            valider.isEnabled = true
    }

    private fun controlImage() {
        image.getEditText()?.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.isEmpty() == true) {
                    image.error = "Image is required"
                    image.isErrorEnabled = true
                } else if (!Patterns.WEB_URL.matcher(p0).matches()) {
                    image.error = "Image URL dismatch"
                    image.isErrorEnabled = true
                } else {
                    image.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                /*val imageUrl = URL(image.getEditText()?.text.toString()).openStream()
                val imageBitmaped: Bitmap? = BitmapFactory.decodeStream(imageUrl)
                imageNeighbor.setImageBitmap(imageBitmaped)*/
            }
        })
    }

    private fun controlName() {
        nom.getEditText()?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.isEmpty() == true) {
                    nom.error = "Name is required"
                    nom.isErrorEnabled = true
                } else {
                    nom.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun controlPhone() {
        phone.getEditText()?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.isEmpty() == true) {
                    phone.error = "Phone is required"
                    phone.isErrorEnabled = true
                } else if (!Patterns.PHONE.matcher(p0).matches()) {
                    phone.error = "Phone dismatch"
                    phone.isErrorEnabled = true
                } else {
                    phone.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun controlWebsite() {
        website.getEditText()?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.isEmpty() == true) {
                    website.error = "WebSite is required"
                    website.isErrorEnabled = true
                } else if (!Patterns.WEB_URL.matcher(p0).matches()) {
                    website.error = "WebSite URL dismatch"
                    website.isErrorEnabled = true
                } else {
                    website.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun controlAdress() {
        adress.getEditText()?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.isEmpty() == true) {
                    adress.error = "Adresse is required"
                    adress.isErrorEnabled = true
                } else {
                    adress.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun controlAboutMe() {
        aboutme.getEditText()?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0?.isEmpty() == true) {
                    aboutme.error = "aboutme is required"
                    aboutme.isErrorEnabled = true
                } else {
                    aboutme.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun countNeighbour(): Int {
        return NeighborRepository.getInstance().getNeighbours().size
    }

    private fun addNeighbour(n: Neighbor) {
        NeighborRepository.getInstance().addNeighbour(n)
    }
}
