import java.awt.Color;
import java.awt.Graphics;

public abstract class Vehicle implements ITransport {
	/// <summary>         
    /// Левая координата отрисовки автомобиля         
    /// </summary>         
    protected float _startPosX; 

    /// <summary>         
    /// Правая кооридната отрисовки автомобиля         
    /// </summary>         
    protected float _startPosY; 

    /// <summary>         
    /// Ширина окна отрисовки         
    /// </summary>         
    protected int _pictureWidth;

    /// <summary>   
    /// Высота окна отрисовки 
    /// </summary>  
    protected int _pictureHeight; 

  /// <summary>         
    /// Максимальная скорость         
    /// </summary>         
    public int maxSpeed;
    public int getMaxSpeed() {
    	return maxSpeed;
    }
    protected void setMaxSpeed(int maxSpeed) {
    	this.maxSpeed = maxSpeed;
    } 
    
    /// <summary>         
    /// Вес автомобиля        
    /// </summary>         
    public float weight;
    public float getWeight() {
    	return weight;
    }
    protected void setWeight(float weight) {
    	this.weight = weight;
    }    
    
    /// <summary>   
    /// Основной цвет кузова         
    /// </summary>         
    public Color mainColor;
    public Color getMainColor() {
    	return mainColor;
    }
    protected void setMainColor(Color mainColor) {
    	this.mainColor = mainColor;
    }
    @Override
    public void setMainColor(String colorName) {
        switch (colorName) {
            case "yellow":
                mainColor = Color.YELLOW;
                break;
            case "blue":
                mainColor = Color.BLUE;
                break;
            case "red":
                mainColor = Color.RED;
                break;
            case "green":
                mainColor = Color.GREEN;
                break;
            case "black":
                mainColor = Color.BLACK;
                break;
            case "orange":
                mainColor = Color.ORANGE;
                break;
            case "grey":
                mainColor = Color.GRAY;
                break;
            case "white":
                mainColor = Color.WHITE;
                break;
        }
    }
    
    @Override
    public String colorToString(Color color) {
        if (color.equals(Color.WHITE)) {
            return "white";
        } else if (color.equals(Color.BLACK)) {
            return "black";
        } else if (color.equals(Color.RED)) {
            return "red";
        } else if (color.equals(Color.YELLOW)) {
            return "yellow";
        } else if (color.equals(Color.ORANGE)) {
            return "orange";
        } else if (color.equals(Color.BLUE)) {
            return "blue";
        } else if (color.equals(Color.GRAY)) {
            return "gray";
        } else if (color.equals(Color.GREEN)) {
            return "green";
        }
        return "white";
    }
    
    public void SetPosition(int x, int y, int width, int height)
    {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }

    public abstract void DrawTractor(Graphics g);

    public abstract void MoveTransport(Direction direction);
}
