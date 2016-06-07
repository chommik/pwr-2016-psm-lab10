package pl.chom.lab10;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {

    private int entryId;

    private Button saveButton;
    private TextView inputId;
    private EditText inputContent;

    private WebService service = new WebService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        saveButton = (Button) findViewById(R.id.button);
        inputId = (TextView) findViewById(R.id.inputId);
        inputContent = (EditText) findViewById(R.id.inputContent);

        entryId = getIntent().getIntExtra("id", -1);

        setInitialValues();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveItem();
            }
        });
    }

    private void saveItem() {
        if (service.setItemValue(entryId, inputContent.toString())) {
            Snackbar.make(findViewById(R.id.layout), "Zapisano pomyślnie.", Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(findViewById(R.id.layout), "Błąd podczas zapisywania.", Snackbar.LENGTH_SHORT).show();
        }
    }

    private void setInitialValues() {
        if (entryId == -1) {
            inputId.setText("(nowy)");
        } else {
            inputId.setText(String.valueOf(entryId));
            inputContent.setText(service.getItem(entryId));
        }
    }
}
