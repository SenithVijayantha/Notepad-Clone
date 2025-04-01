import org.w3c.dom.Attr;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class NotepadClone extends JFrame {
    final private JTextPane textPane;
    final private JFileChooser fileChooser;

    public NotepadClone() {


//        Set up the frame
        setTitle("Notepad Clone");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

//        Components
        textPane = new JTextPane();
        fileChooser = new JFileChooser();
        
//        Change the font
        Font textAreaFont = new Font("Consolas", Font.PLAIN, 14);
        textPane.setFont(textAreaFont);

//        Change the color of text pane
        textPane.setBackground(Color.BLUE);
        textPane.setForeground(Color.YELLOW);

//        Add text area with scroll functionality
        JScrollPane scrollPane = new JScrollPane(textPane);
        add(scrollPane, BorderLayout.CENTER);

//        Create Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu textStyles = new JMenu("Style");
        menuBar.add(fileMenu);
        menuBar.add(textStyles);

//        Create Menu items
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        JMenuItem boldTextItem = new JMenuItem("<html><b>Bold</b></html>");
        JMenuItem italicTextItem = new JMenuItem("<html><i>Italic</i></html>");
        JMenuItem underlineTextItem = new JMenuItem("<html><u>Underline</u></html>");

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        textStyles.add(boldTextItem);
        textStyles.add(italicTextItem);
        textStyles.add(underlineTextItem);

//        Add menu bar to frame
        setJMenuBar(menuBar);

//        Actions listeners
        boldTextItem.addActionListener(e -> makeSelectedTextBold());
        italicTextItem.addActionListener(e -> makeSelectedTextItalic());
    }

    private void makeSelectedTextItalic() {
        StyledDocument doc = textPane.getStyledDocument();
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();

        if (start != end) { // Checks whether there's a selection or not
            AttributeSet attributes = doc.getCharacterElement(start).getAttributes();
            boolean isItalic = StyleConstants.isItalic(attributes); // Check actual bold state
            SimpleAttributeSet italic = new SimpleAttributeSet();

            StyleConstants.setItalic(italic, !isItalic);

            doc.setCharacterAttributes(start, end - start, italic, false);
        }


    }

    private void makeSelectedTextBold() {
        StyledDocument doc = textPane.getStyledDocument();
        int start = textPane.getSelectionStart();
        int end = textPane.getSelectionEnd();

        if (start != end) { // Checks whether there's a selection or not
            AttributeSet attributes = doc.getCharacterElement(start).getAttributes();
            boolean isBold = StyleConstants.isBold(attributes); // Check actual bold state
            SimpleAttributeSet bold = new SimpleAttributeSet();

            StyleConstants.setBold(bold, !isBold);

            doc.setCharacterAttributes(start, end - start, bold, false);
        }
    }

    public static void main(String[] args) {
        NotepadClone notepad = new NotepadClone();
        notepad.setVisible(true);
    }
}
