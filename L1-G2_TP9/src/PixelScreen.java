import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class PixelScreen extends JFrame {

	private static final long serialVersionUID = 1L;

	private Dimension screenDimension;
	private Dimension pixelDimension;

	private Screen screen;
	private Thread updater;

	/**
	 * Create a new PixelScreen frame.
	 * The screen is initially black.
	 * @param width number of pixels horizontally.
	 * @param height number of pixels vertically.
	 * @param pixelSize size of a pixel.
	 */
	public PixelScreen(int width, int height, int pixelSize) {
		screenDimension = new Dimension(width*pixelSize + 1, height*pixelSize + 1);
		pixelDimension = new Dimension(width, height);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Pixel Screen");

		this.screen = new Screen(pixelDimension, pixelSize);
		this.screen.setPreferredSize(screenDimension);
		this.setContentPane(screen);
		this.pack();
		this.setMinimumSize(this.getSize());
		this.setMaximumSize(this.getSize());
		this.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - this.getWidth())/2, 
       		 			 (Toolkit.getDefaultToolkit().getScreenSize().height - this.getHeight())/2);

		this.setVisible(true);

		this.updater = new Thread(new ScreenKeeper());
		this.updater.start();
	}

	/**
	 * Update the color of a pixel.
	 * @param x pixel x position.
	 * @param y pixel y position.
	 * @param col new pixel color.
	 */
	public void updatePixel(int x, int y, Color col) {
		this.screen.updatePixel(new ScreenLoc(x, y), col);
	}

	/**
	 * Wait milliseconds milliseconds then refresh the screen.
	 * @param milliseconds time to wait before refreshing.
	 */
	public void refreshAfter(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		refresh();
	}

	/**
	 * Refresh the screen frame.
	 */
	public void refresh() {
		this.screen.repaint();
	}
	
	private static final class ScreenPixel extends JPanel {

		private static final long serialVersionUID = 1L;
		private final int size;
		private Color color;
		
		public ScreenPixel(int size) {
			this.size = size;
			this.color = Color.BLACK;
		}
		
		public void setColor(Color color) {
			this.color = color;
		}

		@Override
		protected void paintComponent(Graphics g) {
			g.setColor(color);
			g.fillRect(0, 0, size, size);
			
			g.setColor(Color.GRAY);
			g.drawLine(0, 0, 0, size);
			g.drawLine(size, 0, 0, 0);
		}
		
	}

	private static final class Screen extends JPanel {

		private static final long serialVersionUID = 1L;

		private Map<ScreenLoc, ScreenPixel> pixels;
		private Dimension pixelDimension;
		private int pixelSize;

		public Screen(Dimension pixelDimension, int pixelSize) {
			super(new GridLayout(pixelDimension.height, pixelDimension.width));
			this.pixelDimension = pixelDimension;
			this.pixelSize = pixelSize;
			this.pixels = new HashMap<>();
			for (int i = 0; i < pixelDimension.width*pixelDimension.height; i++) {
				ScreenPixel pixel = new ScreenPixel(pixelSize);
				this.pixels.put(new ScreenLoc(i%pixelDimension.width, i/pixelDimension.width), pixel);
				add(pixel);
			}
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			g.setColor(Color.GRAY);
			g.drawLine(0, pixelSize*pixelDimension.height,
					   pixelSize*pixelDimension.width, pixelSize*pixelDimension.height);
			g.drawLine(pixelSize*pixelDimension.width, 0,
					   pixelSize*pixelDimension.width, pixelSize*pixelDimension.height);
		}

		public synchronized void updatePixel(ScreenLoc loc, Color col) {
			this.pixels.get(loc).setColor(col);
		}
	}

	private static final class ScreenLoc {
		public final int x, y;
		public ScreenLoc(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ScreenLoc other = (ScreenLoc) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
	}

	private static final class ScreenKeeper implements Runnable {

		public ScreenKeeper() {}

		@Override
		public void run() {
			while(true);
		}

	}

}
