package com.example.simpletodosteph

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FileUtils.readLines
import org.apache.commons.io.FileUtils.readLines
import java.io.File
import java.io.IOException
import java.nio.charset.Charset


class MainActivity : AppCompatActivity() {
    var listOfTasks = mutableListOf<String>()
    lateinit var adapter: TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

                val onLongClickListener = object :

                    TaskItemAdapter.OnLongClickListener {
                    override fun onItemLongClicked(position: Int) {
                        //1. Remove the item from the list
                        listOfTasks.removeAt(position)

                        //2. Notify the adapter that our data set has changed
                        adapter.notifyDataSetChanged()

                        saveItems()
                    }

                }

                //1.let's detect when the user clicks on the add button
//        findViewById<Button>(R.id.button).setOnClickListener{
//            //Code in here is going to be executed when the user clicks on a button
//            Log.i("Steph", "user clicked on button")
                // }

                loadItems()

                //look up recyclerView in layout
                val recyclerView = findViewById<RecyclerView>(R.id.recView)
                //Create adapter passing in the simple user data
                adapter = TaskItemAdapter(listOfTasks, onLongClickListener)
                // Attach the adapter to the recyclerview to populate items
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this)

                // Set up the button in input file so that the user can enter a task end add it to the list
                val inputTextField = findViewById<EditText>(R.id.addTaskField)

                //Get a reference to the button
                // end then set on onclicklistener
                findViewById<Button>(R.id.button).setOnClickListener {

                    // 1.the text the user has inputted into @id/addTask/field
                    val userInputtedTask = inputTextField.text.toString()

                    //2. Add the string to our list of task: listOfTask
                    listOfTasks.add(userInputtedTask)

                    // Notify the adapter that our data has been udapted
                    adapter.notifyItemInserted(listOfTasks.size - 1)

                    //3. reset text field
                    inputTextField.setText("")
                }

            }
    //Save the data that  the user has inputted
    //Save data by writting and reading from a file

    //Get the file we need
    fun getDataFile(): File {
        //Every line is going to reading every line in the data file
        return File(filesDir, "data.txt")
    }

    //Load the items by writing them into our data file
    fun loadItems() {
        try {
            listOfTasks = FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }

    // Save items  by writing them into our data file
    fun saveItems() {
        try {
            FileUtils.writeLines(getDataFile(), listOfTasks)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }

}



