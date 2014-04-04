package br.com.cjv.teste;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.Control;
import javax.sound.sampled.Control.Type;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JFrame;

import br.com.cjv.ImagePanel;

public class ThinLine implements Line{

	  // left and right edges of the line, in "fraction of the way
    // across the visible page"
    private float start=0.25f, finish=0.75f;

    /** Create a new default line, which covers the middle 1/2 of the
	page. */
    public ThinLine() {
	// use defaults
    }

    /**
       Create a new line which covers only part of the width of the
       page.  The parameters are given in "fraction of the way across
       the visible page".

       @param start the left end of the line
       @param finish the right end of the line
    */
    public ThinLine(float start, float finish) {
	this.start = start;
	this.finish = finish;
    }

    public void print(Graphics g, PageFormat pf, float y) {
	Graphics2D g2 = (Graphics2D) g;
	g2.setStroke(new BasicStroke(THICKNESS));

	// margins of printable page
	float left = (float) pf.getImageableX();
	float right = (float) (pf.getImageableX() + pf.getImageableWidth());

	// fraction i'm going to draw
	float realLeft = left + (right-left) * start;
	float realRight = left + (right-left) * finish;

	// draw it
	g2.drawLine((int) realLeft, (int) (y+1), (int) realRight, (int) (y+1));
    }

    public int height(Graphics g) {
	return 2;
    }

    private static final float THICKNESS = 0.1f;

	@Override
	public Info getLineInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void open() throws LineUnavailableException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Control[] getControls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isControlSupported(Type control) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Control getControl(Type control) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addLineListener(LineListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeLineListener(LineListener listener) {
		// TODO Auto-generated method stub
		
	}	
	
	
	public static void main(String[] args) {
		ThinLine tl = new ThinLine();
		final BufferedImage image = new BufferedImage(10, 200, BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();
		tl.print(g, new PageFormat(), 1F);
		ImagePanel panel = new ImagePanel(image);
			panel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					try {
						String path = new File(".").getCanonicalPath();										
						ImageIO.write(image, "png", new File(path+"\\CJVBarcode.png"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}			
			});
		//JLabel linhaDigitavel = new JLabel(arr.getCampo1()+arr.getCampo2()+arr.getCampo3()+arr.getCampo4());
	    JFrame frame = new JFrame();
	    frame.getContentPane().add(panel);
	    //frame.getContentPane().add(linhaDigitavel);	    
	    frame.pack();
	    frame.setVisible(true);				
	}

}
