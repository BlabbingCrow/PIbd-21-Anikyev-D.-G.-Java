import java.awt.Color;
import java.awt.Graphics;

public class TractorBase extends Vehicle {
	/// <summary>         
    /// Ширина отрисовки автомобиля         
    /// </summary>         
    protected int carWidth = 123;

    /// <summary>         
    /// Ширина отрисовки автомобиля         
    /// </summary>         
    protected int carHeight = 55;
	
	
    public TractorBase(int maxSpeed, float weight, Color mainColor)
    {
        setMaxSpeed(maxSpeed);
        setWeight(weight);
        setMainColor(mainColor);
    }

    public TractorBase(String info) {
        String[] parameters = info.split(";");
        if (parameters.length == 3) {
        	setMaxSpeed(Integer.parseInt(parameters[0]));
            setWeight(Integer.parseInt(parameters[1]));
            setMainColor(parameters[2]);
        }
    }
    
	@Override
	public void MoveTransport(Direction direction) {
		float step = maxSpeed * 100 / weight; switch (direction)
        {
            // вправо                 
            case Right:
                if (_startPosX + step < _pictureWidth - carWidth)
                {
                    _startPosX += step;
                }
                break;
            //влево               
            case Left:
                if (_startPosX - step > 0)
                {
                    _startPosX -= step;
                }
                break;
            //вверх          
            case Up:
                if (_startPosY - step > 0)
                {
                    _startPosY -= step;
                }
                break;
            //вниз      
            case Down:
                if (_startPosY + step < _pictureHeight - carHeight)
                {
                    _startPosY += step;
                }
                break;
        }
	}

	@Override
	public void DrawTractor(Graphics g) {
		g.setColor(Color.BLACK);       
        g.drawOval( (int)_startPosX + 20, (int)_startPosY + 20, 30, 30);
        g.drawOval( (int)_startPosX + 90, (int)_startPosY + 30, 20, 20);
        g.drawOval( (int)_startPosX + 100, (int)_startPosY + 15, 5, 10);
        g.drawRect( (int)_startPosX + 25, (int)_startPosY, 35, 20);
        g.drawRect( (int)_startPosX + 25, (int)_startPosY + 20, 75, 20);
        g.drawRect( (int)_startPosX + 80, (int)_startPosY + 5, 5, 15);
    	
        //кузов       
        g.setColor(mainColor);
        g.fillRect( (int)_startPosX + 25, (int)_startPosY, 35, 20);
        g.fillRect( (int)_startPosX + 25, (int)_startPosY + 20, 75, 20);
        g.fillRect( (int)_startPosX + 25, (int)_startPosY - 3, 40, 3);

        //труба
        g.setColor(Color.GRAY);
        g.fillRect( (int)_startPosX + 80, (int)_startPosY + 5, 5, 15);

        //задние фары            
        g.setColor(Color.RED);
        g.fillRect( (int)_startPosX + 25, (int)_startPosY + 10, 10, 10);

        //передние фары       
        g.setColor(Color.YELLOW);
        g.fillRect( (int)_startPosX + 100, (int)_startPosY + 15, 5, 10);

        //стекла         
        g.setColor(Color.WHITE);
        g.fillRect( (int)_startPosX + 37, (int)_startPosY + 1, 23, 18);

        //колеса
        g.setColor(Color.BLACK);
        g.fillOval( (int)_startPosX + 20, (int)_startPosY + 20, 30, 30);
        g.fillOval( (int)_startPosX + 90, (int)_startPosY + 30, 20, 20);
        g.setColor(Color.GRAY);
        g.fillOval( (int)_startPosX + 25, (int)_startPosY + 25, 20, 20);
        g.fillOval( (int)_startPosX + 95, (int)_startPosY + 35, 10, 10);
	}
	
	public String toString() {
        return maxSpeed + ";" + weight + ";" + colorToString(mainColor);
    }
}
