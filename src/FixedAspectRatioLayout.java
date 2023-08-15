import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

public class FixedAspectRatioLayout implements LayoutManager
{
    @Override
    public void addLayoutComponent(String name, Component comp) { }

    @Override
    public void layoutContainer(Container parent)
    {
        if (parent.getComponentCount() == 0)
            return;

        Component component = parent.getComponent(0);

        Insets insets = parent.getInsets();
        int maxWidth = parent.getWidth() - (insets.left + insets.right);
        int maxHeight = parent.getHeight() - (insets.top + insets.bottom);

        Dimension preferredSize = component.getPreferredSize();
        Dimension targetDimension = getScaledDimension(preferredSize, new Dimension(maxWidth, maxHeight));

        double width = targetDimension.getWidth();
        double height = targetDimension.getHeight();

        double x = (maxWidth - width) / 2.0;
        double y = (maxHeight - height) / 2.0;

        component.setBounds((int) x, (int) y, (int) width, (int) height);
    }

    private Dimension getScaledDimension(Dimension imageSize, Dimension boundary)
    {
        double widthRatio = boundary.getWidth() / imageSize.getWidth();
        double heightRatio = boundary.getHeight() / imageSize.getHeight();
        double scale = Math.min(widthRatio, heightRatio);
        return new Dimension((int) (imageSize.width * scale), (int) (imageSize.height * scale));
    }

    @Override
    public Dimension minimumLayoutSize(Container parent)
    {
        return preferredLayoutSize(parent);
    }

    @Override
    public Dimension preferredLayoutSize(Container parent)
    {
        if (parent.getComponentCount() == 0)
            return new Dimension(0, 0);

        return parent.getComponent(0).getPreferredSize();
    }

    @Override
    public void removeLayoutComponent(Component parent) { }
}
