import javax.swing.*;

public class MainForm {

    private JPanel mainPanel;
    private JPanel buttonsPanel;
    private JButton collapseButton;
    private JButton expandButton;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JTextField patronymicTextField;
    private JTextField FIOTextField;
    private JLabel wrongFormatLabel;
    private JLabel FIOLabel;
    private JLabel patronymicLabel;
    private JLabel surnameLabel;
    private JLabel nameLabel;
    private String FIOText;

    public MainForm(){
        wrongFormatLabel.setVisible(false);
        expandButton.setVisible(false);
        FIOTextField.setVisible(false);
        FIOLabel.setVisible(false);

        collapseButton.addActionListener(collapse -> {
            if (nameTextField.getText().matches("\\S+") && surnameTextField.getText().matches("\\S+") && (patronymicTextField.getText().matches("") || patronymicTextField.getText().matches("\\S+"))) {
                wrongFormatLabel.setVisible(false);
                nameTextField.setVisible(false);
                nameLabel.setVisible(false);
                surnameTextField.setVisible(false);
                surnameLabel.setVisible(false);
                patronymicTextField.setVisible(false);
                patronymicLabel.setVisible(false);

                FIOText =  nameTextField.getText() + " " + surnameTextField.getText() + " " + patronymicTextField.getText();

                FIOLabel.setVisible(true);
                FIOTextField.setText(FIOText);
                FIOTextField.setVisible(true);
                expandButton.setVisible(true);
            }
            else {
                wrongFormatLabel.setVisible(true);
            }
        });

        expandButton.addActionListener(expand -> {
            String[] FIOText = FIOTextField.getText().split("\\s+");
            if (FIOText.length > 1 && FIOText.length < 4 && FIOTextField.getText().matches("\\S?.+")) {
                wrongFormatLabel.setVisible(false);
                FIOTextField.setVisible(false);
                FIOLabel.setVisible(false);
                collapseButton.setVisible(true);

                nameTextField.setText(FIOText[0]);
                surnameTextField.setText(FIOText[1]);
                if (FIOText.length == 3) {
                    patronymicTextField.setText(FIOText[2]);
                } else {
                    patronymicTextField.setText("");
                }

                nameTextField.setVisible(true);
                nameLabel.setVisible(true);
                surnameTextField.setVisible(true);
                surnameLabel.setVisible(true);
                patronymicTextField.setVisible(true);
                patronymicLabel.setVisible(true);
            } else {
                wrongFormatLabel.setVisible(true);
            }
        });


    }

    public JPanel getMainPanel(){
        return mainPanel;
    }
}
