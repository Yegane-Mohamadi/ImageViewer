import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class ImageViewerGUI extends JFrame implements ActionListener {
    JButton selectFileButton = new JButton("Choose Image");
    JButton showImageButton= new JButton("Show Image");
    JButton resizeButton= new JButton("Resize");
    JButton grayscaleButton= new JButton("Gray Scale");
    JButton brightnessButton= new JButton("Brightness");
    JButton closeButton= new JButton("Exit");
    JButton showResizeButton= new JButton("Show Resized Image");
    JButton showBrightnessButton=new JButton("Show Brightness Image");
    JButton backButton=new JButton("Back");
    JTextField widthTextField=new JTextField();
    JTextField heightTextField=new JTextField();
    JTextField brightnessTextField=new JTextField();
    File file;
    String filePath = "/home/...";
    JFileChooser fileChooser = new JFileChooser(filePath);
    int h = 900;
    int w = 1200;
    float brightenFactor = 1;
    BufferedImage img;

    ImageViewerGUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Image Viewer");
        this.setSize(700, 400);
        selectFileButton.addActionListener(this);
        showImageButton.addActionListener(this);
        brightnessButton.addActionListener(this);
        grayscaleButton.addActionListener(this);
        closeButton.addActionListener(this);
        resizeButton.addActionListener(this);
        backButton.addActionListener(this);
        showResizeButton.addActionListener(this);
        showBrightnessButton.addActionListener(this);
        this.setVisible(true);
        this.setResizable(true);
        mainPanel();
    }

    private void mainPanel() {
        JPanel mainpanel = new JPanel();
        mainpanel.setLayout(null);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 2));
        buttonsPanel.setBounds(150,100,400,200);
        JLabel label = new JLabel("IMAGE VIEWER");
        label.setBounds(300 , 50 ,100 ,50);
        buttonsPanel.add(this.selectFileButton);
        buttonsPanel.add(this.showImageButton);
        buttonsPanel.add(this.brightnessButton);
        buttonsPanel.add(this.grayscaleButton);
        buttonsPanel.add(this.resizeButton);
        buttonsPanel.add(this.closeButton);
        mainpanel.add(label);
        mainpanel.add(buttonsPanel);
        this.add(mainpanel);

    }
    public void resizepanel(){
        JPanel resizePanel = new JPanel();
        resizePanel.setLayout(null);
        JLabel width = new JLabel("Width : ");
        width.setBounds(50 , 50 , 100 , 100);
        widthTextField.setBounds(100 , 70 , 100 , 50);
        JLabel Height = new JLabel("Height : ");
        Height.setBounds(300 , 50 , 100 , 100);
        heightTextField.setBounds(350 , 70 , 100 , 50);
        backButton.setBounds(100 , 200 , 200 , 50);
        showResizeButton.setBounds(350 , 200 , 200 , 50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resizePanel.setVisible(false);
                getContentPane().removeAll();
                mainPanel();
                revalidate();
                repaint();
            }
        });
        resizePanel.add(Height);
        resizePanel.add(width);
        resizePanel.add(heightTextField);
        resizePanel.add(widthTextField);
        resizePanel.add(backButton);
        resizePanel.add(showResizeButton);
        this.add(resizePanel);
    }
    public void showResizeImage(int w, int h){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        JLabel picturelabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(String.valueOf(file)).getImage().getScaledInstance(w,h,Image.SCALE_DEFAULT));
        picturelabel.setIcon(imageIcon);
        tempPanel.add(picturelabel);
        tempPanel.setSize(w, h);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(w, h);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }
    public void chooseimage(){
        int choice = fileChooser.showOpenDialog(this);
        if(choice == JFileChooser.APPROVE_OPTION){
            file = fileChooser.getSelectedFile();
            filePath=file.getPath();
            System.out.println(filePath);
            try {
                img = ImageIO.read(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            showOriginalImage();
        }
        }

    private void showOriginalImage() {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        JLabel picturelabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(1000,500,Image.SCALE_SMOOTH));
        picturelabel.setIcon(imageIcon);
        tempPanel.add(picturelabel);
        tempPanel.setSize(900, 500);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(900, 500);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }
    public void brightnessPanel(){
        JPanel brightnessPanel = new JPanel();
        brightnessPanel.setLayout(null);
        JLabel brightness = new JLabel("Enter a number for brightness:  ");
        brightness.setBounds(50 , 50 , 200 , 100);
        brightnessTextField.setBounds(250 , 70 , 100 , 50);
        showBrightnessButton.setBounds(350 , 200 , 200 , 50);
        backButton.setBounds(100 , 200 , 200 ,50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                brightnessPanel.setVisible(false);
                getContentPane().removeAll();
                mainPanel();
                revalidate();
                repaint();
            }
        });
        brightnessPanel.add(brightness);
        brightnessPanel.add(brightnessTextField);
        brightnessPanel.add(showBrightnessButton);
        brightnessPanel.add(backButton);
        this.add(brightnessPanel);
    }
    private void showBrightnessImage(float brightenFactor) {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        BufferedImage image = img;
        RescaleOp op = new RescaleOp(brightenFactor, 0, null);
        image = op.filter(image, image);
        ImageIcon mage = new ImageIcon(image.getScaledInstance(1000,500,Image.SCALE_SMOOTH));;
        JLabel age = new JLabel();
        age.setIcon(mage);
        tempPanel.add(age);
        tempPanel.setSize(1000, 500);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1000, 500);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }
    public void grayScaleImage(){
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(1000,500,Image.SCALE_SMOOTH));
        Image image = imageIcon.getImage();
        ImageFilter filter = new GrayFilter(true, 10);
        ImageProducer producer = new FilteredImageSource(image.getSource(), filter);
        Image mage = Toolkit.getDefaultToolkit().createImage(producer);
        JLabel picLabel = new JLabel(new ImageIcon(mage));
        tempPanel.add(picLabel);
        tempPanel.setSize(1000, 500);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1000, 500);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == resizeButton){
            getContentPane().removeAll();
            resizepanel();
            revalidate();
            repaint();

        }
        else if(e.getSource()== selectFileButton) {
            chooseimage();
        }

        else if(e.getSource()== showImageButton) {
            showOriginalImage();
        }
        else if(e.getSource()== showResizeButton) {
            h = Integer.parseInt(heightTextField.getText());
            w = Integer.parseInt(widthTextField.getText());
            showResizeImage(w, h);
        }
    else if(e.getSource()==brightnessButton) {
        this.getContentPane().removeAll();
        this.brightnessPanel();
        this.revalidate();
        this.repaint();
    }
        else if(e.getSource()== showBrightnessButton){
            brightenFactor = Float.parseFloat(brightnessTextField.getText());
            showBrightnessImage(brightenFactor);

        }
        else if(e.getSource()==grayscaleButton) {
            grayScaleImage();
        }
        else if(e.getSource()==closeButton){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }
}