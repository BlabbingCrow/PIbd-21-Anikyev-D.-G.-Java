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
