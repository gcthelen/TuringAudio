package main.directory;
import javax.swing.*;
import java.awt.*;

public class SampleBrowserView extends JPanel{

	private int cornerX = 10;
	private int cornerY = 10;
	private static final long serialVersionUID = 460120758786887240L;
	private SampleBrowser parent;

	SampleBrowserView(SampleBrowser parent) {
		this.parent = parent;
	}
	
    protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
    	super.paintComponent(g);
		int currentX = cornerX;
		int currentY = cornerY;
		int indent = 14;
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		for(Visible visible: parent.getVisibleFiles()) {
			g2.setColor(Color.WHITE);
			Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
			g2.setFont(font);
			if(visible.isDirectory()) {
				g2.setColor(Color.ORANGE);
				font = new Font(Font.SANS_SERIF, Font.BOLD, 12);
				g2.setFont(font);
			}
			FontMetrics metrics = g2.getFontMetrics(font);
			int hgt = metrics.getHeight();
			int adv = metrics.stringWidth(visible.getName());
			int cornerX = currentX + indent * visible.getDepth();
			Rectangle rect = new Rectangle(cornerX - hgt / 2, currentY, adv + hgt, hgt);
			g2.fill(rect);
			visible.setMouseArea(rect);
			g2.setColor(Color.BLACK);
			g2.drawString(visible.getName(), cornerX, currentY + hgt - hgt / 4);
			currentY += hgt + 2;
		}
    }

}
