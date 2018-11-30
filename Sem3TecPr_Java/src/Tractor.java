import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Tractor extends TractorBase{
    /// <summary>         
    /// Дополнительный цвет         
    /// </summary>         
    private Color dopColor;
    public Color getDopColor() {
    	return dopColor;
    }
    private void setDopColor(Color dopColor) {
    	this.dopColor = dopColor;
    }
    public void setDopColor(String colorName) {
        switch (colorName) {
            case "yellow":
            	dopColor = Color.YELLOW;
                break;
            case "blue":
            	dopColor = Color.BLUE;
                break;
            case "red":
            	dopColor = Color.RED;
                break;
            case "green":
            	dopColor = Color.GREEN;
                break;
            case "black":
            	dopColor = Color.BLACK;
                break;
            case "orange":
            	dopColor = Color.ORANGE;
                break;
            case "grey":
            	dopColor = Color.GRAY;
                break;
            case "white":
            	dopColor = Color.WHITE;
                break;
        }

    }
    
    /// <summary>         
    /// Признак наличия переднего спойлера         
    /// </summary>         
    private boolean frontEquipment;
    public boolean getFrontEquipment() {
    	return frontEquipment;
    }
    private void setFrontEquipment(boolean frontEquipment) {
    	this.frontEquipment = frontEquipment;
    }
    
    /// <summary>         
    /// Признак наличия заднего спойлера         
    /// </summary>         
    private boolean backEquipment;
    public boolean getBackEquipment() {
    	return backEquipment;
    }
    private void setBackEquipment(boolean backEquipment) {
    	this.backEquipment = backEquipment;
    }
    
    /// <summary>         
    /// Конструктор         
    /// </summary>         
    /// <param name="maxSpeed">Максимальная скорость</param>         
    /// <param name="weight">Вес автомобиля</param>         
    /// <param name="mainColor">Основной цвет кузова</param>       
    /// <param name="dopColor">Дополнительный цвет</param>         
    public Tractor(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean frontEquipment, boolean backEquipment)   
    {
    	super(maxSpeed, weight, mainColor);
        setDopColor(dopColor);
        setFrontEquipment(frontEquipment);
        setBackEquipment(backEquipment);
    } 


    /// <summary>        
    /// Отрисовка автомобиля      
    /// </summary>      
    /// <param name="g"></param>     
    public void DrawTractor(Graphics g)
    {  	
        // отрисуем сперва переднее оборудование автомобиля           
        if (frontEquipment)
        {
            Polygon points = new Polygon();
            points.addPoint((int)_startPosX + 103, (int)_startPosY + 35);
            points.addPoint((int)_startPosX + 113, (int)_startPosY + 10);
            points.addPoint((int)_startPosX + 123, (int)_startPosY + 50);
            g.setColor(dopColor); 
            g.fillPolygon(points);
            g.setColor(Color.black); 
            g.drawPolygon(points);
        }
        // рисуем заднее оборудование автомобиля       
        if (backEquipment)
        {
        	Polygon points = new Polygon();
            points.addPoint((int)_startPosX + 25, (int)_startPosY + 35);
            points.addPoint((int)_startPosX + 10, (int)_startPosY + 30);
            points.addPoint((int)_startPosX, (int)_startPosY + 50);
            points.addPoint((int)_startPosX + 10, (int)_startPosY + 40);
            g.setColor(dopColor); 
            g.fillPolygon(points);
            g.setColor(Color.black); 
            g.drawPolygon(points);
        }

        // теперь отрисуем основной кузов автомобиля 
        super.DrawTractor(g);

        g.setColor(dopColor); 
        // рисуем гоночные полоски       
        g.fillRect( (int)_startPosX + 55, (int)_startPosY + 25, 45, 5);
    }
}
