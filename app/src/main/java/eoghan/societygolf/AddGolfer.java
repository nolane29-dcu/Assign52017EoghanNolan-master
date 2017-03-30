package eoghan.societygolf;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import eoghan.societygolf.GolferContract.GolferEntry;

/**
 * Allows user to create a new golfer or edit an existing one.
 */
public class AddGolfer extends AppCompatActivity {

    /** EditText field to enter the golfer's name */
    private EditText mNameEditText;

    /** EditText field to enter the golfer's handicap */
    private EditText mHandicapEditText;

    /** EditText field to enter the golfer's contact number */
    private EditText mPhoneEditText;

    /** EditText field to enter the golfer's gender */
    private Spinner mGenderSpinner;

    private int mGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_golfer);

        // Find all relevant views that we will need to read user input from
        mNameEditText = (EditText) findViewById(R.id.editGolferName);
        mHandicapEditText = (EditText) findViewById(R.id.editGolferHandicap);
        mPhoneEditText = (EditText) findViewById(R.id.editGolferPhone);
        mGenderSpinner = (Spinner) findViewById(R.id.spinner_gender);

        setupSpinner();
    }

    /**
     * Setup the dropdown spinner that allows the user to select the gender of the pet.
     */
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mGenderSpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = GolferEntry.GENDER_MALE;
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender = GolferEntry.GENDER_FEMALE;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = GolferEntry.GENDER_MALE;
            }
        });
    }

    /**
     * Get user input from editor and save new golfer into database.
     */
    private void insertGolfer() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String nameString = mNameEditText.getText().toString().trim();
        String breedString = mHandicapEditText.getText().toString().trim();
        String weightString = mPhoneEditText.getText().toString().trim();
        int weight = Integer.parseInt(weightString);

        // Create database helper
        DatabaseHelper mDbHelper = new DatabaseHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and golfer attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(GolferEntry.COLUMN_GOLFER_NAME, nameString);
        values.put(GolferEntry.COLUMN_HANDICAP, breedString);
        values.put(GolferEntry.COLUMN_GENDER, mGender);
        values.put(GolferEntry.COLUMN_PHONENO, weight);

        // Insert a new row for a golfer in the database, returning the ID of that new row.
        long newRowId = db.insert(GolferEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving golfer", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Golfer saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    public void clearGolfer () {
        mNameEditText.setText("");
        mHandicapEditText.setText("");
        mPhoneEditText.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save golfer to database
                insertGolfer();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Clear" menu option
            case R.id.action_clear:
                clearGolfer();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case R.id.action_back:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}