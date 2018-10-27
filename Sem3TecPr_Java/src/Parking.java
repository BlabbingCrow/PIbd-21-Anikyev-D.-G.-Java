import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/// <summary>
    /// Параметризованны класс для хранения набора объектов от интерфейса ITransport
    /// </summary>
    /// <typeparam name="T"></typeparam>
public class Parking<T extends ITransport> {
	/// <summary>
    /// Массив объектов, которые храним
    /// </summary>
    private ArrayList<T> _places;
    
    /// <summary>
    /// Ширина окна отрисовки
    /// </summary>
    private int pictureWidth;
    public int getPictureWidth() {
    	return pictureWidth;
    }
    public void setPictureWidth(int pictureWidth) {
    	this.pictureWidth = pictureWidth;
    }
    
    /// <summary>
    /// Высота окна отрисовки
    /// </summary>
    private int pictureHeight;
    public int getPictureHeight() {
    	return pictureHeight;
    }
    public void setPictureHeight(int pictureHeight) {
    	this.pictureHeight = pictureHeight;
    }
    
    /// <summary>
    /// Размер парковочного места (ширина)
    /// </summary>
    private int _placeSizeWidth = 210;
    
    /// <summary>
    /// Размер парковочного места (высота)
    /// </summary>
    private int _placeSizeHeight = 80;
    
    /// <summary>
    /// Конструктор
    /// </summary>
    /// <param name="sizes">Количество мест на парковке</param>
    /// <param name="pictureWidth">Рамзер парковки - ширина</param>
    /// <param name="pictureHeight">Рамзер парковки - высота</param>
    public Parking(int sizes, int pictureWidth, int pictureHeight)
    {
        _places = new ArrayList<T>();
        setPictureWidth( pictureWidth);
        setPictureHeight( pictureHeight);
        for (int i = 0; i < sizes; i++)
        {
            _places.add(null);
        }
    }
    
    /// <summary>
    /// Перегрузка оператора сложения
    /// Логика действия: на парковку добавляется автомобиль
    /// </summary>
    /// <param name="p">Парковка</param>
    /// <param name="car">Добавляемый автомобиль</param>
    /// <returns></returns>
    public int Add(T car)
    {
        for (int i = 0; i < _places.size(); i++)
        {
            if (CheckFreePlace(i))
            {
                _places.add(i, car);
                _places.get(i).SetPosition(5 + i / 5 * _placeSizeWidth + 5, i % 5 * _placeSizeHeight + 15, getPictureWidth(), getPictureHeight());
                return i;
            }
        }
        return -1;
    }
    
    /// <summary>
    /// Перегрузка оператора вычитания
    /// Логика действия: с парковки забираем автомобиль
    /// </summary>
    /// <param name="p">Парковка</param>
    /// <param name="index">Индекс места, с которого пытаемся извлечь объект</param>
    /// <returns></returns>
    public T Remove(int index)
    {
        if (index < 0 || index > _places.size())
        {
            return null;
        }
        if (!CheckFreePlace(index))
        {
            T car = _places.get(index);
            _places.set(index, null);
            return car;
        }
        return null;
    }
    
    /// <summary>
    /// Метод проверки заполнености парковочного места (ячейки массива)
    /// </summary>
    /// <param name="index">Номер парковочного места (порядковый номер вмассиве)</param>
    /// <returns></returns>
    private boolean CheckFreePlace(int index)
    {
        return _places.get(index) == null;
    }
    
    /// <summary>
    /// Метод отрисовки парковки
    /// </summary>
    /// <param name="g"></param>
    public void Draw(Graphics g)
    {
        DrawMarking(g);
        for (int i = 0; i < _places.size(); i++)
        {
            if (!CheckFreePlace(i))
            {
                //если место не пустое
                _places.get(i).DrawTractor(g);
            }
        }
    }
    /// <summary>
    /// Метод отрисовки разметки парковочных мест
    /// </summary>
    /// <param name="g"></param>
    private void DrawMarking(Graphics g)
    {
    	g.setColor(Color.BLACK);
        //границы праковки
        g.drawRect( 0, 0, (_places.size() / 5) * _placeSizeWidth, 480);
        for (int i = 0; i < _places.size() / 5; i++)
        {
            //отрисовываем, по 5 мест на линии
            for (int j = 0; j < 6; ++j)
            {
                //линия рамзетки места
                g.drawLine( i * _placeSizeWidth, j * _placeSizeHeight, i * _placeSizeWidth + 110, j * _placeSizeHeight);
            }
            g.drawLine( i * _placeSizeWidth, 0, i * _placeSizeWidth, 400);
        }
    }
}
