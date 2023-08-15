import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class Program
{
    public static final int PITCH_WIDTH = 74;
    public static final int PITCH_HEIGHT = 115;
    public static final double CENTER_CIRCLE_RADIUS = 10;
    public static final double PENALTY_AREA_WIDTH = 44;
    public static final double PENALTY_AREA_HEIGHT = 18;
    public static final double PENALTY_SPOT_DISTANCE = 12;
    public static final double PENALTY_ARC_RADIUS = 10;
    public static final double GOAL_AREA_WIDTH = 20;
    public static final double GOAL_AREA_HEIGHT = 6;
    public static final double GOAL_WIDTH = 8;
    public static final double LINE_WIDTH = 0.416667;

    public static final Rectangle2D PITCH = new Rectangle2D.Double(0, 0, PITCH_WIDTH, PITCH_HEIGHT);
    public static final Line2D HALFWAY_LINE = new Line2D.Double(0,  PITCH_HEIGHT / 2.0, PITCH_WIDTH, PITCH_HEIGHT / 2.0);
    public static final Ellipse2D CENTRE_CIRCLE = new Ellipse2D.Double(PITCH_WIDTH / 2.0 - CENTER_CIRCLE_RADIUS, PITCH_HEIGHT / 2.0 - CENTER_CIRCLE_RADIUS, CENTER_CIRCLE_RADIUS * 2, CENTER_CIRCLE_RADIUS * 2);
    public static final Rectangle2D PENALTY_AREA_1 = new Rectangle2D.Double((PITCH_WIDTH - PENALTY_AREA_WIDTH) / 2.0, 0, PENALTY_AREA_WIDTH, PENALTY_AREA_HEIGHT);
    public static final Rectangle2D PENALTY_AREA_2 = new Rectangle2D.Double((PITCH_WIDTH - PENALTY_AREA_WIDTH) / 2.0, PITCH_HEIGHT - PENALTY_AREA_HEIGHT, PENALTY_AREA_WIDTH, PENALTY_AREA_HEIGHT);
    public static final Ellipse2D PENALTY_SPOT_1 = new Ellipse2D.Double(PITCH_WIDTH / 2.0 - LINE_WIDTH / 2.0, PENALTY_SPOT_DISTANCE - LINE_WIDTH / 2.0, LINE_WIDTH, LINE_WIDTH);
    public static final Ellipse2D PENALTY_SPOT_2 = new Ellipse2D.Double(PITCH_WIDTH / 2.0 - LINE_WIDTH / 2.0, PITCH_HEIGHT - PENALTY_SPOT_DISTANCE - LINE_WIDTH / 2.0, LINE_WIDTH, LINE_WIDTH);
    public static final double PENALTY_ARC_HALF_ANGLE = Math.toDegrees(Math.acos((PENALTY_AREA_HEIGHT - PENALTY_SPOT_DISTANCE) / PENALTY_ARC_RADIUS));
    public static final Arc2D PENALTY_ARC_1 = new Arc2D.Double(PITCH_WIDTH / 2.0 - PENALTY_ARC_RADIUS, PENALTY_SPOT_DISTANCE - PENALTY_ARC_RADIUS, PENALTY_ARC_RADIUS * 2, PENALTY_ARC_RADIUS * 2, 270 - PENALTY_ARC_HALF_ANGLE, PENALTY_ARC_HALF_ANGLE * 2, Arc2D.OPEN);
    public static final Arc2D PENALTY_ARC_2 = new Arc2D.Double(PITCH_WIDTH / 2.0 - PENALTY_ARC_RADIUS, PITCH_HEIGHT - PENALTY_SPOT_DISTANCE - PENALTY_ARC_RADIUS, PENALTY_ARC_RADIUS * 2, PENALTY_ARC_RADIUS * 2, 90 - PENALTY_ARC_HALF_ANGLE, PENALTY_ARC_HALF_ANGLE * 2, Arc2D.OPEN);
    public static final Rectangle2D GOAL_AREA_1 = new Rectangle2D.Double((PITCH_WIDTH - GOAL_AREA_WIDTH) / 2.0, 0, GOAL_AREA_WIDTH, GOAL_AREA_HEIGHT);
    public static final Rectangle2D GOAL_AREA_2 = new Rectangle2D.Double((PITCH_WIDTH - GOAL_AREA_WIDTH) / 2.0, PITCH_HEIGHT - GOAL_AREA_HEIGHT, GOAL_AREA_WIDTH, GOAL_AREA_HEIGHT);


    public static final Color GRASS_COLOR = new Color(17, 153, 19);
    public static final Color LINE_COLOR = new Color(255, 255, 255);

    public static void main(String[] args)
    {
        new Program();
    }

    private Program()
    {
        JFrame frame = new JFrame("Football");
        JPanel pitch = new JPanel()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);

                scaleGraphicsContext(g);
                Graphics2D g2d = (Graphics2D)g;

                g2d.setColor(LINE_COLOR);

                g2d.setStroke(new BasicStroke((float)LINE_WIDTH * 2));
                g2d.draw(PITCH);

                g2d.setStroke(new BasicStroke((float)LINE_WIDTH));
                g2d.draw(HALFWAY_LINE);
                g2d.draw(CENTRE_CIRCLE);
                g2d.draw(PENALTY_AREA_1);
                g2d.draw(PENALTY_AREA_2);
                g2d.draw(GOAL_AREA_1);
                g2d.draw(GOAL_AREA_2);
                g2d.draw(PENALTY_ARC_1);
                g2d.draw(PENALTY_ARC_2);
                g2d.draw(PENALTY_SPOT_1);
                g2d.draw(PENALTY_SPOT_2);
            }

            private void scaleGraphicsContext(Graphics g)
            {
                Graphics2D g2d = (Graphics2D)g;

                double widthRatio = (double)getWidth() / (double)PITCH_WIDTH;
                double heightRatio = (double)getHeight() / (double)PITCH_HEIGHT;

                double scale = Math.max(widthRatio, heightRatio);

                double pitchWidth = PITCH_WIDTH * scale;
                double pitchHeight = PITCH_HEIGHT * scale;

                double translateX = (getWidth() - pitchWidth) / 2;
                double translateY = (getHeight() - pitchHeight) / 2;

                g2d.translate(translateX, translateY);
                g2d.scale(scale, scale);
            }
        };

        pitch.setBackground(GRASS_COLOR);
        pitch.setPreferredSize(new Dimension(PITCH_WIDTH, PITCH_HEIGHT));

        JPanel panel = new JPanel(new FixedAspectRatioLayout());
        panel.add(pitch);
        panel.setBackground(Color.BLACK);

        frame.getContentPane().add(panel);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


}
