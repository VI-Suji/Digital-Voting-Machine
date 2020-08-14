import java.awt.*;
import javax.swing.*;

public class SSCCE extends JPanel
{
    public SSCCE()
    {
        JPanel blue = new JPanel();
        blue.setBackground( Color.BLUE );
        blue.setPreferredSize( new Dimension(500, 100) );

        Box box = Box.createHorizontalBox();

        JPanel green = new JPanel();
        green.setBackground( Color.GREEN );
        green.setPreferredSize( new Dimension(200, 100) );
        green.setMaximumSize( green.getPreferredSize() );
        box.add( green );

        JPanel cyan = new JPanel();
        cyan.setBackground( Color.CYAN );
        cyan.setPreferredSize( new Dimension(300, 100) );
        cyan.setMinimumSize( cyan.getPreferredSize() );
        box.add( cyan );

        setLayout( new BorderLayout() );
        add(blue, BorderLayout.NORTH);
        add(box, BorderLayout.CENTER);
        add(green, BorderLayout.EAST);
    }

    private static void createAndShowGUI()
    {
        JFrame frame = new JFrame("SSCCE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SSCCE());
        frame.setLocationByPlatform( true );
        frame.pack();
        frame.setVisible( true );
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                createAndShowGUI();
            }
        });
    }
}