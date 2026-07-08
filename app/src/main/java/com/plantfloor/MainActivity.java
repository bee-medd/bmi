package com.plantfloor;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etMessage;

    private TextView tvGoal;
    private TextView tvTone;

    private Button btnSell;
    private Button btnApologize;
    private Button btnNegotiate;
    private Button btnFollowUp;

    private Button btnProfessional;
    private Button btnFriendly;
    private Button btnShort;

    private Button btnGenerate;
    private Button btnClear;

    private TextView tvReplyOne;
    private TextView tvReplyTwo;
    private TextView tvReplyThree;

    private Button btnCopyOne;
    private Button btnCopyTwo;
    private Button btnCopyThree;

    private String selectedGoal = "sell";
    private String selectedTone = "professional";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupListeners();
        updateUiState();
    }

    private void initViews() {
        etMessage = findViewById(R.id.etMessage);

        tvGoal = findViewById(R.id.tvGoal);
        tvTone = findViewById(R.id.tvTone);

        btnSell = findViewById(R.id.btnSell);
        btnApologize = findViewById(R.id.btnApologize);
        btnNegotiate = findViewById(R.id.btnNegotiate);
        btnFollowUp = findViewById(R.id.btnFollowUp);

        btnProfessional = findViewById(R.id.btnProfessional);
        btnFriendly = findViewById(R.id.btnFriendly);
        btnShort = findViewById(R.id.btnShort);

        btnGenerate = findViewById(R.id.btnGenerate);
        btnClear = findViewById(R.id.btnClear);

        tvReplyOne = findViewById(R.id.tvReplyOne);
        tvReplyTwo = findViewById(R.id.tvReplyTwo);
        tvReplyThree = findViewById(R.id.tvReplyThree);

        btnCopyOne = findViewById(R.id.btnCopyOne);
        btnCopyTwo = findViewById(R.id.btnCopyTwo);
        btnCopyThree = findViewById(R.id.btnCopyThree);
    }

    private void setupListeners() {
        btnSell.setOnClickListener(v -> {
            selectedGoal = "sell";
            updateUiState();
        });

        btnApologize.setOnClickListener(v -> {
            selectedGoal = "apologize";
            updateUiState();
        });

        btnNegotiate.setOnClickListener(v -> {
            selectedGoal = "negotiate";
            updateUiState();
        });

        btnFollowUp.setOnClickListener(v -> {
            selectedGoal = "follow_up";
            updateUiState();
        });

        btnProfessional.setOnClickListener(v -> {
            selectedTone = "professional";
            updateUiState();
        });

        btnFriendly.setOnClickListener(v -> {
            selectedTone = "friendly";
            updateUiState();
        });

        btnShort.setOnClickListener(v -> {
            selectedTone = "short";
            updateUiState();
        });

        btnGenerate.setOnClickListener(v -> generateReplies());

        btnClear.setOnClickListener(v -> {
            etMessage.setText("");
            tvReplyOne.setText("Your first reply will appear here.");
            tvReplyTwo.setText("Your second reply will appear here.");
            tvReplyThree.setText("Your third reply will appear here.");
            Toast.makeText(this, "Cleared", Toast.LENGTH_SHORT).show();
        });

        btnCopyOne.setOnClickListener(v -> copyReply(tvReplyOne.getText().toString()));
        btnCopyTwo.setOnClickListener(v -> copyReply(tvReplyTwo.getText().toString()));
        btnCopyThree.setOnClickListener(v -> copyReply(tvReplyThree.getText().toString()));
    }

    private void updateUiState() {
        tvGoal.setText("Goal: " + getGoalName());
        tvTone.setText("Tone: " + getToneName());

        setChipState(btnSell, selectedGoal.equals("sell"));
        setChipState(btnApologize, selectedGoal.equals("apologize"));
        setChipState(btnNegotiate, selectedGoal.equals("negotiate"));
        setChipState(btnFollowUp, selectedGoal.equals("follow_up"));

        setChipState(btnProfessional, selectedTone.equals("professional"));
        setChipState(btnFriendly, selectedTone.equals("friendly"));
        setChipState(btnShort, selectedTone.equals("short"));
    }

    private void setChipState(Button button, boolean selected) {
        if (selected) {
            button.setBackgroundResource(R.drawable.bg_chip_selected);
            button.setTextColor(getResources().getColor(android.R.color.white));
        } else {
            button.setBackgroundResource(R.drawable.bg_chip);
            button.setTextColor(getResources().getColor(android.R.color.black));
        }
    }

    private String getGoalName() {
        switch (selectedGoal) {
            case "sell":
                return "Sell";
            case "apologize":
                return "Apologize";
            case "negotiate":
                return "Negotiate";
            case "follow_up":
                return "Follow up";
            default:
                return "Sell";
        }
    }

    private String getToneName() {
        switch (selectedTone) {
            case "professional":
                return "Professional";
            case "friendly":
                return "Friendly";
            case "short":
                return "Short";
            default:
                return "Professional";
        }
    }

    private void generateReplies() {
        String message = etMessage.getText().toString().trim();

        if (message.isEmpty()) {
            Toast.makeText(this, "Paste the customer message first", Toast.LENGTH_SHORT).show();
            return;
        }

        String replyOne;
        String replyTwo;
        String replyThree;

        if (selectedGoal.equals("sell")) {
            replyOne = "Hello! Thank you for your interest. Yes, this option is available, and I’d be happy to help you complete your order today.";
            replyTwo = "Hi! This is a great choice. I can guide you through the next step and reserve it for you if you’d like.";
            replyThree = "Yes, it’s available. Many customers choose it because of its quality and value. Would you like me to confirm it for you?";
        } else if (selectedGoal.equals("apologize")) {
            replyOne = "Hello, I sincerely apologize for the inconvenience. Thank you for your patience, and I’ll do my best to resolve this as quickly as possible.";
            replyTwo = "I’m really sorry about this. I understand your concern, and I’ll make sure we handle it properly.";
            replyThree = "Thank you for letting us know. We apologize for the issue and appreciate the opportunity to make it right.";
        } else if (selectedGoal.equals("negotiate")) {
            replyOne = "Thank you for your interest. At the moment, this is our best price, but I can offer a small discount if you confirm today.";
            replyTwo = "I understand your request. The price reflects the quality of the product, but I’d be happy to find a fair option for both sides.";
            replyThree = "I appreciate your offer. I can make a small adjustment while still maintaining the quality and service you expect.";
        } else {
            replyOne = "Hi! I just wanted to follow up and see if you’re still interested. I’d be happy to answer any questions.";
            replyTwo = "Hello, I hope you’re doing well. I’m checking in to see if you’d like to continue with your order.";
            replyThree = "Just a quick follow-up. The offer is still available, and I can help you complete everything whenever you’re ready.";
        }

        if (selectedTone.equals("short")) {
            replyOne = makeShortReply();
            replyTwo = makeShortReplyAlternative();
            replyThree = makeShortReplySales();
        } else if (selectedTone.equals("friendly")) {
            replyOne = replyOne + " 😊";
            replyTwo = replyTwo + " 😊";
            replyThree = replyThree + " 😊";
        }

        tvReplyOne.setText(replyOne);
        tvReplyTwo.setText(replyTwo);
        tvReplyThree.setText(replyThree);

        Toast.makeText(this, "Replies generated", Toast.LENGTH_SHORT).show();
    }

    private String makeShortReply() {
        if (selectedGoal.equals("sell")) {
            return "Yes, it’s available. I can help you confirm the order today.";
        } else if (selectedGoal.equals("apologize")) {
            return "I’m sorry for the inconvenience. I’ll help resolve this as soon as possible.";
        } else if (selectedGoal.equals("negotiate")) {
            return "This is our best price, but I can offer a small discount today.";
        } else {
            return "Hi! Just following up to see if you’re still interested.";
        }
    }

    private String makeShortReplyAlternative() {
        if (selectedGoal.equals("sell")) {
            return "Yes, we still have it. Would you like me to reserve it for you?";
        } else if (selectedGoal.equals("apologize")) {
            return "Sorry about that. I understand, and I’ll fix it as quickly as possible.";
        } else if (selectedGoal.equals("negotiate")) {
            return "I can offer a small discount if you confirm now.";
        } else {
            return "Hello! Are you still interested in continuing with your order?";
        }
    }

    private String makeShortReplySales() {
        if (selectedGoal.equals("sell")) {
            return "Great choice. I can confirm it for you today.";
        } else if (selectedGoal.equals("apologize")) {
            return "We apologize and appreciate the chance to make it right.";
        } else if (selectedGoal.equals("negotiate")) {
            return "I appreciate your offer. Let’s find a fair option.";
        } else {
            return "The offer is still available if you’d like to continue.";
        }
    }

    private void copyReply(String text) {
        if (text == null || text.trim().isEmpty() || text.contains("will appear here")) {
            Toast.makeText(this, "No reply to copy", Toast.LENGTH_SHORT).show();
            return;
        }

        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("ReplyBoss AI Reply", text);
        clipboard.setPrimaryClip(clip);

        Toast.makeText(this, "Reply copied", Toast.LENGTH_SHORT).show();
    }
}
