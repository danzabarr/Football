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

                Rectangle2D pitch = new Rectangle2D.Double(0, 0, PITCH_WIDTH, PITCH_HEIGHT);
                Line2D halfWayLine = new Line2D.Double(0,  PITCH_HEIGHT / 2.0, PITCH_WIDTH, PITCH_HEIGHT / 2.0);
                Ellipse2D centreCircle = new Ellipse2D.Double(PITCH_WIDTH / 2.0 - CENTER_CIRCLE_RADIUS, PITCH_HEIGHT / 2.0 - CENTER_CIRCLE_RADIUS, CENTER_CIRCLE_RADIUS * 2, CENTER_CIRCLE_RADIUS * 2);

                Rectangle2D penaltyArea1 = new Rectangle2D.Double((PITCH_WIDTH - PENALTY_AREA_WIDTH) / 2.0, 0, PENALTY_AREA_WIDTH, PENALTY_AREA_HEIGHT);
                Rectangle2D penaltyArea2 = new Rectangle2D.Double((PITCH_WIDTH - PENALTY_AREA_WIDTH) / 2.0, PITCH_HEIGHT - PENALTY_AREA_HEIGHT, PENALTY_AREA_WIDTH, PENALTY_AREA_HEIGHT);

                Point2D penaltySpot1 = new Point2D.Double(PITCH_WIDTH / 2.0, PENALTY_SPOT_DISTANCE);
                Point2D penaltySpot2 = new Point2D.Double(PITCH_WIDTH / 2.0, PITCH_HEIGHT - PENALTY_SPOT_DISTANCE);

                Ellipse2D penaltySpotCircle1 = new Ellipse2D.Double(penaltySpot1.getX() - LINE_WIDTH / 2.0, penaltySpot1.getY() - LINE_WIDTH / 2.0, LINE_WIDTH, LINE_WIDTH);
                Ellipse2D penaltySpotCircle2 = new Ellipse2D.Double(penaltySpot2.getX() - LINE_WIDTH / 2.0, penaltySpot2.getY() - LINE_WIDTH / 2.0, LINE_WIDTH, LINE_WIDTH);

                double arcExtent = Math.toDegrees(Math.acos((PENALTY_AREA_HEIGHT - PENALTY_SPOT_DISTANCE) / PENALTY_ARC_RADIUS));

                Arc2D penaltyArc1 = new Arc2D.Double(PITCH_WIDTH / 2.0 - PENALTY_ARC_RADIUS, PENALTY_SPOT_DISTANCE - PENALTY_ARC_RADIUS, PENALTY_ARC_RADIUS * 2, PENALTY_ARC_RADIUS * 2, 270 - arcExtent, arcExtent * 2, Arc2D.OPEN);
                Arc2D penaltyArc2 = new Arc2D.Double(PITCH_WIDTH / 2.0 - PENALTY_ARC_RADIUS, PITCH_HEIGHT - PENALTY_SPOT_DISTANCE - PENALTY_ARC_RADIUS, PENALTY_ARC_RADIUS * 2, PENALTY_ARC_RADIUS * 2, 90 - arcExtent, arcExtent * 2, Arc2D.OPEN);

                Rectangle2D goalArea1 = new Rectangle2D.Double((PITCH_WIDTH - GOAL_AREA_WIDTH) / 2.0, 0, GOAL_AREA_WIDTH, GOAL_AREA_HEIGHT);
                Rectangle2D goalArea2 = new Rectangle2D.Double((PITCH_WIDTH - GOAL_AREA_WIDTH) / 2.0, PITCH_HEIGHT - GOAL_AREA_HEIGHT, GOAL_AREA_WIDTH, GOAL_AREA_HEIGHT);

                g2d.setColor(LINE_COLOR);
                g2d.setStroke(new BasicStroke((float)LINE_WIDTH * 2));

                g2d.draw(pitch);

                g2d.setStroke(new BasicStroke((float)LINE_WIDTH));
                g2d.draw(halfWayLine);
                g2d.draw(centreCircle);
                g2d.draw(penaltyArea1);
                g2d.draw(penaltyArea2);
                g2d.draw(goalArea1);
                g2d.draw(goalArea2);
                g2d.draw(penaltyArc1);
                g2d.draw(penaltyArc2);
                g2d.draw(penaltySpotCircle1);
                g2d.draw(penaltySpotCircle2);
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
        frame.setVisible(true);
    }


}
