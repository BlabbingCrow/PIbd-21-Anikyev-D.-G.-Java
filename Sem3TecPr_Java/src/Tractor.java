import java.awt.Color;
import java.awt.Graphics;
import java.util.Set;

public class Tractor {
	/// <summary>         
    /// Левая координата отрисовки автомобиля         
    /// </summary>   
    private float _startPosX;

    /// <summary>        
    /// Правая кооридната отрисовки автомобиля         
    /// </summary>         
    private float _startPosY; 

    /// <summary>         
    /// Ширина окна отрисовки         
    /// </summary>         
    private int _pictureWidth;

    //Высота окна отрисовки         
    private int _pictureHeight; 

    /// <summary>         
    ///Ширина отрисовки автомобиля         
    /// </summary>         
    private int carWidth = 123; 

    /// <summary>         
    /// Ширина отрисовки автомобиля         
    /// </summary>         
    private int carHeight = 55; 

    /// <summary>         
    /// Максимальная скорость         
    /// </summary>         
    private int maxSpeed;
    public int getMaxSpeed() {
    	return maxSpeed;
    }
    public void setMaxSpeed(int maxSpeed) {
    	this.maxSpeed = maxSpeed;
    } 
    
    /// <summary>         
    /// Вес автомобиля        
    /// </summary>         
    private float weight;
    public float getWeight() {
    	return weight;
    }
    public void setWeight(float weight) {
    	this.weight = weight;
    }    
    
    /// <summary>   
    /// Основной цвет кузова         
    /// </summary>         
    private Color mainColor;
    public Color getMainColor() {
    	return mainColor;
    }
    public void setMainColor(Color mainColor) {
    	this.mainColor = mainColor;
    }
    
    /// <summary>         
    /// Дополнительный цвет         
    /// </summary>         
    private Color dopColor;
    public Color getDopColor() {
    	return dopColor;
    }
    public void setDopColor(Color dopColor) {
    	this.dopColor = dopColor;
    }

    /// <summary>         
    /// Конструктор         
    /// </summary>         
    /// <param name="maxSpeed">Максимальная скорость</param>         
    /// <param name="weight">Вес автомобиля</param>         
    /// <param name="mainColor">Основной цвет кузова</param>       
    /// <param name="dopColor">Дополнительный цвет</param>         
    public Tractor(int maxSpeed, float weight, Color mainColor, Color dopColor)
    {
        setMaxSpeed(maxSpeed);
        setWeight(weight);
        setMainColor(mainColor);
        setDopColor(dopColor);
    } 

    /// <summary>         
    /// Установка позиции автомобиля         
    /// </summary>         
    /// <param name="x">Координата X</param>         
    /// <param name="y">Координата Y</param>         
    /// <param name="width">Ширина картинки</param>         
    /// <param name="height">Высота картинки</param>         
    public void SetPosition(int x, int y, int width, int height)
    {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }

    /// <summary>         
    /// Изменение направления пермещения         
    /// </summary>         
    /// <param name="direction">Направление</param> 
    
    public void MoveTransport(Direction direction)
    {
        float step = maxSpeed * 100 / weight;
        switch (direction)
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

    /// <summary>        
    /// Отрисовка автомобиля      
    /// </summary>      
    /// <param name="g"></param>     
    public void DrawTractor(Graphics g)
    {
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
        g.setColor(dopColor);
        g.fillRect( (int)_startPosX + 25, (int)_startPosY + 25, 75, 5);

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
}
