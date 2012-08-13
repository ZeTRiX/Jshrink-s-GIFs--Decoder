package zetrix;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ZeTRiX
 */
public class Desh extends JFrame {
  static byte[] ByteArr;
  static String[] StrArr = new String[256];
  static int[] intern = new int[256];
  public JButton Go = new JButton("Decode image", (new ImageIcon(zetrix.settings.Util.getRes("go.png"))));
  public static JLabel RText = new JLabel("Result:");
  public static JLabel IText = new JLabel("Int:");
  public static Font MainFont = new Font("Arial", Font.BOLD, 14);
  public static JTextField ITextF = new JTextField(20);
  public static JTextPane Result;
  public static JTabbedPane Main;
  public static Box MainBox;
  public static Box FileBox;
  public static Box AboutBox;
  
    private JButton GChooseB = new JButton("Choose GIF", (new ImageIcon(zetrix.settings.Util.getRes("add.png"))));
    private JFileChooser GChooser = new JFileChooser();
  
  public static JLabel About = new JLabel("Choose the .gif file first.");
  public static JLabel About1 = new JLabel("Then enter an int values into the \"int\" field!");
  public static JLabel About2 = new JLabel("Now push the \"Decode\" button! And copy the result");
  public static JLabel About3 = new JLabel("from the \"Result\" field.");
  public static JLabel About4 = new JLabel("It's a Jshrink-Obfuscator's GIF's Decoder");
  public static JLabel About5 = new JLabel("(c) Coded by ZeTRiX (Evgen Yanov)");
  
  public Desh() {
        setTitle("GIF's Decoder (Coded by ZeTRiX)");
        setIconImage(zetrix.settings.Util.getRes("ficon.png"));
        setBackground(Color.BLACK);
        this.setBounds(200, 200, 480, 220);
        this.setMaximumSize(new Dimension(1087, 572));
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final ImageIcon icon = new ImageIcon(Desh.class.getResource("/zetrix/img/bgn.png"));
        Main = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT) {
            protected void paintComponent(Graphics g) {
                g.drawImage(icon.getImage(), 0,0, null);
                super.paintComponent(g);
            }
        };
        
        FileNameExtensionFilter GCHFF = new FileNameExtensionFilter(
                "GIF image to decode", "gif");
        GChooser.setAcceptAllFileFilterUsed(false);
        GChooser.setFileFilter(GCHFF);
        
        Box box1 = Box.createHorizontalBox();
        IText.setFont(MainFont);
        box1.add(IText);
        box1.add(Box.createHorizontalStrut(6));
        box1.add(ITextF);
        Box box2 = Box.createHorizontalBox();
        RText.setFont(MainFont);
        box2.add(RText);
        box2.add(Box.createHorizontalStrut(6));
        Result = new JTextPane() {
            private static final long serialVersionUID = 1L;
        };
        Result.setText("Result Will Appear here. Instruction is in the \"About\" tab!");
        box2.add(Result);
        Box box4 = Box.createHorizontalBox();
        box4.add(GChooseB);
        box4.add(Box.createHorizontalGlue());
        box4.add(Box.createHorizontalStrut(12));
        box4.add(Go);
        IText.setPreferredSize(RText.getPreferredSize());
        MainBox = Box.createVerticalBox();
        MainBox.setBorder(new EmptyBorder(12,12,12,12));
        MainBox.add(box1);
        MainBox.add(Box.createVerticalStrut(12));
        MainBox.add(box2);
        MainBox.add(Box.createVerticalStrut(17));
        MainBox.add(box4);
        Main.add("Decoder", MainBox);
        
        Box ab0 = Box.createHorizontalBox();
        Box ab1 = Box.createVerticalBox();
        ab1.add(About);
        ab1.add(About1);
        ab1.add(About2);
        ab1.add(About3);
        ab0.add(ab1);
        Box ab2 = Box.createHorizontalBox();
        ab2.add(About4);
        ab2.add(Box.createHorizontalStrut(6));
        Box ab3 = Box.createHorizontalBox();
        ab3.add(About5);
        AboutBox = Box.createVerticalBox();
        AboutBox.setBorder(new EmptyBorder(12,12,12,12));
        AboutBox.add(ab0);
        AboutBox.add(Box.createVerticalStrut(8));
        AboutBox.add(ab2);
        AboutBox.add(Box.createVerticalStrut(6));
        AboutBox.add(ab3);
        Main.add("About", AboutBox);
        
        this.getContentPane().add(Main);
        
        GChooseB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnVal = GChooser.showOpenDialog(GChooseB);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    File GFile = GChooser.getSelectedFile();
                    InputStream is = null;
                    OutputStream os = null;
                    try {
                        File GIFF = new File("to_decode.gif");
                        
                        is = new FileInputStream(GFile);
                        os = new FileOutputStream(GIFF);
                        
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = is.read(buffer)) > 0) {
                            os.write(buffer, 0, length);
                        }
                        is.close();
                        os.close();
                        System.out.println("File is choosed successfully!");
                        
                        JOptionPane.showMessageDialog((Component)
                                null,
                                "Image choosed successful. \n Now enter int value and push \"Decode\" button. \n",
                                "Uploaded - OK",
                                JOptionPane.OK_OPTION);
                    } catch (IOException ex){
                        System.out.println(ex.toString());
                        JOptionPane.showMessageDialog((Component)
                                null,
                                "Image cannot be choosed. \n Error IOException found. \n Try again. \n",
                                "Warning - Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        Go.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final int decodebase = Integer.parseInt(ITextF.getText().toString());
                Result.setText(Desh(decodebase));
            }
        });
  }
  
    public static synchronized String Desh(int paramInt) {
        int i = paramInt & 0xFF;
        if (intern[i] != paramInt) {
            intern[i] = paramInt;
            if (paramInt < 0)
                paramInt &= 65535;
            String str = new String(ByteArr, paramInt, ByteArr[(paramInt - 1)] & 0xFF).intern();
            StrArr[i] = str;
        }
        return StrArr[i];
    }
    
    static {
        try {
            InputStream localInputStream = new FileInputStream("to_decode.gif");
            if (localInputStream != null) {
                int i = localInputStream.read() << 16 | localInputStream.read() << 8 | localInputStream.read();
                ByteArr = new byte[i];
                int j = 0;
                int k = (byte)i;
                byte[] arrayOfByte = ByteArr;
                while (i != 0) {
                    int m = localInputStream.read(arrayOfByte, j, i);
                    if (m == -1)
                        break;
                    i -= m;
                    m += j;
                    while (j < m) {
                        int tmp146_145 = j;
                        byte[] tmp146_143 = arrayOfByte;
                        tmp146_143[tmp146_145] = (byte)(tmp146_143[tmp146_145] ^ k);
                        j++;
                    }
                }
                localInputStream.close();
            }
        } catch (Exception localException) {
            System.out.println("Error: " + localException);
        }
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        Desh Desh = new Desh();
        Desh.show();
    }
}
