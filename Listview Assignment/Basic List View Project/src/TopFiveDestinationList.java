import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

public class TopFiveDestinationList {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	TopDestinationListFrame topDestinationListFrame = new TopDestinationListFrame();
                topDestinationListFrame.setTitle("Top 5 Destination List");
                topDestinationListFrame.setVisible(true);
            }
        });
    }
}


class TopDestinationListFrame extends JFrame {
    private DefaultListModel listModel;

    public TopDestinationListFrame() {
        super("Top Five Destination List");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 750);

        listModel = new DefaultListModel();

        String dest1 ="Rural Beauty of the Arctic, with modern technology!\n " 
                       + " Photo: Bep, https://commons.wikimedia.org/wiki/File:Urnes_Stave_Church_from_north.jpg";
        String dest2 = " Go down under and have a true Aussie adventure! " + " Photo: Elekhh  https://commons.wikimedia.org/wiki/File:Sydney_Opera_House_-_Dec_2008.jpg";
        String dest3 = " Rocky Mountains, meet high elegance in the gem of Canada!" + " Photo: Manske  https://commons.wikimedia.org/wiki/File:Banff_Avenue,_Banff_(7853823354).jpg";
        String dest4 = "The Last frontier with all the modern amenities " + "Photo: Dinker02289 https://commons.wikimedia.org/wiki/File:USS_Anchorage_in_Anchorage,_Alaska.jpg";
        String dest5 = "History, scenary and Chocolate! Need we say more? " + "Photo: Laurent https://commons.wikimedia.org/wiki/File:001_Chateau_de_Chillon_and_Dents_du_Midi_Photo_by_Giles_Laurent.jpg";
        
        //Make updates to your top 5 list below. Import the new image files to resources directory.
        addDestinationNameAndPicture("1. Norway " + "\n" + dest1, new ImageIcon(getClass().getResource("/resources/Nordic.jpg")));
        addDestinationNameAndPicture("2. Sydney, Australia. " + dest2, new ImageIcon(getClass().getResource("/resources/Sydney.jpg")));
        addDestinationNameAndPicture("3. Banff, Alberta, Canada. " + dest3, new ImageIcon(getClass().getResource("/resources/Banff.jpg")));
        addDestinationNameAndPicture("4. Anchorage, Alaska USA. " + dest4, new ImageIcon(getClass().getResource("/resources/Anchorage.jpg")));
        addDestinationNameAndPicture("5. Switzerland. " + dest5, new ImageIcon(getClass().getResource("/resources/Swiss.jpg")));
        
        
        
        JList list = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(list);

        TextAndIconListCellRenderer renderer = new TextAndIconListCellRenderer(2);
        
        list.setBackground(Color.BLUE);
        list.setSelectionBackground(Color.WHITE);
        list.setCellRenderer(renderer);
        
        JLabel nameLabel = new JLabel("Developer: Aaron Shipley");

        getContentPane().add(nameLabel, BorderLayout.NORTH);
        
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    private void addDestinationNameAndPicture(String text, Icon icon) {
        TextAndIcon tai = new TextAndIcon(text, icon);
        listModel.addElement(tai);
    }
}


class TextAndIcon {
    private String text;
    private Icon icon;

    public TextAndIcon(String text, Icon icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}


class TextAndIconListCellRenderer extends JLabel implements ListCellRenderer {
    private static final Border NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    private Border insideBorder;

    public TextAndIconListCellRenderer() {
        this(0, 0, 0, 0);
    }

    public TextAndIconListCellRenderer(int padding) {
        this(padding, padding, padding, padding);
    }

    public TextAndIconListCellRenderer(int topPadding, int rightPadding, int bottomPadding, int leftPadding) {
        insideBorder = BorderFactory.createEmptyBorder(topPadding, leftPadding, bottomPadding, rightPadding);
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value,
    int index, boolean isSelected, boolean hasFocus) {
        // The object from the combo box model MUST be a TextAndIcon.
        TextAndIcon tai = (TextAndIcon) value;

        // Sets text and icon on 'this' JLabel.
        setText(tai.getText());
        setIcon(tai.getIcon());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        Border outsideBorder;

        if (hasFocus) {
            outsideBorder = UIManager.getBorder("List.focusCellHighlightBorder");
        } else {
            outsideBorder = NO_FOCUS_BORDER;
        }

        setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
        setComponentOrientation(list.getComponentOrientation());
        setEnabled(list.isEnabled());
        setFont(list.getFont());

        return this;
    }

    // The following methods are overridden to be empty for performance
    // reasons. If you want to understand better why, please read:
    //
    // http://java.sun.com/javase/6/docs/api/javax/swing/DefaultListCellRenderer.html#override

    public void validate() {}
    public void invalidate() {}
    public void repaint() {}
    public void revalidate() {}
    public void repaint(long tm, int x, int y, int width, int height) {}
    public void repaint(Rectangle r) {}
}