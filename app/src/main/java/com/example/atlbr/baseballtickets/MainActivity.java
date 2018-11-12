package com.example.atlbr.baseballtickets;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.telephony.SmsManager;



public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    //Setup Edittext for user to enter phone number for receipt SMS
    EditText phoneNumber;
    //Setup checkbox for SMS receipt option
    CheckBox receiveText;

    Button sendSMS;
    //String variables used to create receipt
    String receiptString,gameSelectionString, ticketSelectionString, numTicketsString, ballPark,phoneNumString;

    //integer flags used to validate spinner selection
    int gameFlag, ticketFlag, numFlag, ticketCost, ticketCount, totalCost= 0;
    double longitude, latitude = 0.0d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup action bar with title and icon
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Baseball Ticket Purchase");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.baseball);

        phoneNumber = findViewById(R.id.phoneNumber);
        receiveText = findViewById(R.id.receiveText);
        sendSMS = findViewById(R.id.sendSMS);


        //Create listener for checkbox to show edittext field when checked
        receiveText.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(receiveText.isChecked()) {
                    phoneNumber.setVisibility(View.VISIBLE);
                    sendSMS.setVisibility(View.VISIBLE);
                }
                else{
                    phoneNumber.setVisibility(View.INVISIBLE);
                }
            }
        });



        //Setup spinner widgets
        final Spinner gameSelection = findViewById(R.id.gameSelection);
        ArrayAdapter gameAdapter = ArrayAdapter.createFromResource(this,R.array.gameSelectionArray, android.R.layout.simple_spinner_item);
        gameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gameSelection.setAdapter(gameAdapter);

        final Spinner ticketSelection = findViewById(R.id.ticketType);
        ArrayAdapter ticketAdapter = ArrayAdapter.createFromResource(this, R.array.ticketTypeArray, android.R.layout.simple_spinner_item);
        ticketAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ticketSelection.setAdapter(ticketAdapter);

        final Spinner numTickets = findViewById(R.id.numTickets);
        ArrayAdapter numTicketsAdapter = ArrayAdapter.createFromResource(this, R.array.numTicketsArray, android.R.layout.simple_spinner_item );
        numTicketsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numTickets.setAdapter(numTicketsAdapter);

        //Setup buttons and listeners
        final Button displayReceipt = findViewById(R.id.displayReceipt);
        final Button showLocation = findViewById(R.id.showLocation);



        //Setup listener for each spinner, each of which changes a flag variable to indicate a selection has been made
        //Each also updates the correct variables where needed for the receipt output
        gameSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                //Set longitude and latitude for ball park based on selection
                switch(position)
                {
                    case 1:
                        latitude = 41.948437;
                        longitude = -87.655334;
                        ballPark = "Wrigley Field";
                        break;
                    case 2:
                        latitude = 41.948437;
                        longitude = -87.655334;
                        ballPark = "Wrigley Field";
                        break;
                    case 3:
                        latitude = 33.8908;
                        longitude = -84.4678;
                        ballPark = "SunTrust Park";
                        break;
                    case 4:
                        latitude = 33.8908;
                        longitude = -84.4678;
                        ballPark = "SunTrust Park";
                        break;
                    case 5:
                        latitude = 33.8908;
                        longitude = -84.4678;
                        ballPark = "SunTrust Park";
                        break;
                    case 6:
                        latitude = 39.097392;
                        longitude = -84.506858;
                        ballPark = "Great American Ball Park";
                        break;
                    case 7:
                        latitude = 39.097392;
                        longitude = -84.506858;
                        ballPark = "Great American Ball Park";
                        break;
                    case 8:
                        latitude = 39.906057200;
                        longitude = -75.166495200;
                        ballPark = "Citizens Bank Park";
                        break;
                    case 9:
                        latitude = 27.768224600;
                        longitude = -82.653392100;
                        ballPark = "Tropicana Field";
                        break;
                    case 10:
                        latitude = 27.768224600;
                        longitude = -82.653392100;
                        ballPark = "Tropicana Field";
                        break;

                    default:
                        longitude = 0.0;
                        latitude = 0.0;

                }
                //position will not be 0 if user selected an item from the spinner
                if(position!= 0) {
                    gameFlag = 1;
                    gameSelectionString = gameSelection.getSelectedItem().toString();
                    Toast.makeText(MainActivity.this, "You chose: " + gameSelectionString, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Do nothing
            }
        });


        ticketSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                //Update ticket type each time a selection is made
                    switch(position)
                    {
                        case 1:
                            ticketCost = 35;
                            break;

                        case 2:
                            ticketCost = 50;
                            break;

                        case 3:
                            ticketCost = 72;
                            break;

                        case 4:
                            ticketCost = 95;
                            break;

                        default:
                            ticketCost = 0;

                    }

                        //position will not be 0 if user selected an item from the spinner
                    if(position !=0) {
                    ticketFlag = 1;
                    ticketSelectionString = ticketSelection.getSelectedItem().toString();
                    Toast.makeText(MainActivity.this, "Ticket Selected: " + ticketSelectionString, Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Do nothing
            }
        });

        numTickets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                //Update ticket count to corresponding selection, each time a selection is made
                switch(position)
                {
                    case 1:
                        ticketCount = 1;
                        break;

                    case 2:
                        ticketCount = 2;
                        break;

                    case 3:
                        ticketCount = 3;
                        break;

                    case 4:
                        ticketCount = 4;
                        break;

                    case 5:
                        ticketCount = 5;
                        break;

                    case 6:
                        ticketCount = 6;
                        break;

                    default:
                        ticketCount = 0;
                }

                //position will not be 0 if user selected an item from the spinner
                if(position != 0){
                    numFlag = 1;
                    numTicketsString = numTickets.getSelectedItem().toString();
                    Toast.makeText(MainActivity.this, "You selected " + position + " tickets", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Do nothing
            }
        });


        //Setup listeners for buttons
        displayReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Validate spinner selections, send toast messages if nothing selected
            if(gameFlag != 1)
            {
                Toast.makeText(MainActivity.this, "Please Select a Game", Toast.LENGTH_LONG).show();
            }

            else if(ticketFlag != 1)
            {
                Toast.makeText(MainActivity.this, "Please Select a Ticket Type", Toast.LENGTH_LONG).show();
            }

            else if(numFlag != 1)
            {
                Toast.makeText(MainActivity.this, "Please Select the Number of Tickets", Toast.LENGTH_LONG).show();
            }

            else {

                //Calculate total cost before sending the user to receipt activity
                totalCost = ticketCost * ticketCount;

                    //Create string for receipt
                receiptString= "******Receipt For Ticket Purchase******" +
                                "\n\nGame Selected: " + gameSelectionString +
                                "\nTicket Type: " + ticketSelectionString +
                                "\nNumber of Tickets: " + numTicketsString +
                                "\n\nTotal Cost: $" + totalCost +
                                "\n\nSee you at the game!";


                    //Create intent for receipt activity, and send string data for receipt
                    Intent receiptActivity = new Intent(getBaseContext(), DisplayReceipt.class);

                    receiptActivity.putExtra("receipt",receiptString);
                    startActivity(receiptActivity);
                 }
            }
        });

        sendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Calculate total cost before sending the user to receipt activity
                totalCost = ticketCost * ticketCount;

                //Create string for receipt
                receiptString = "******Receipt For Ticket Purchase******" +
                        "\n\nGame Selected: " + gameSelectionString +
                        "\nTicket Type: " + ticketSelectionString +
                        "\nNumber of Tickets: " + numTicketsString +
                        "\n\nTotal Cost: $" + totalCost +
                        "\n\nSee you at the game!";

                sendSMSMessage();
            }
        });


        showLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent locationActivity = new Intent(getBaseContext(),StadiumLocationActivity.class);

                //Send longitude and latitude to location activity
                locationActivity.putExtra("longitude",longitude);
                locationActivity.putExtra("latitude",latitude);
                locationActivity.putExtra("parkName",ballPark);
                startActivity(locationActivity);

            }
        });
    }
    protected void sendSMSMessage() {
        phoneNumString = phoneNumber.getText().toString();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumString, null, receiptString, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }
}
