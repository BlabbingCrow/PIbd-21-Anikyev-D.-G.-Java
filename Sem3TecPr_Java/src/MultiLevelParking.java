import java.util.ArrayList;


/// <summary>
/// Класс-хранидище парковок
/// </summary>
public class MultiLevelParking {
	/// <summary>
	/// Список с уровнями парковки
	/// </summary>
	ArrayList<Parking<ITransport>> parkingStages;
	
	/// <summary>
	/// Сколько мест на каждом уровне
	/// </summary>
	private final int countPlaces = 20;
	
	/// <summary>
    /// Конструктор
    /// </summary>
    /// <param name="countStages">Количество уровенй парковки</param>
    /// <param name="pictureWidth"></param>
    /// <param name="pictureHeight"></param>
    public MultiLevelParking(int countStages, int pictureWidth, int pictureHeight)
    {
        parkingStages = new ArrayList<Parking<ITransport>>();
        for (int i = 0; i < countStages; ++i)
        {
            parkingStages.add(new Parking<ITransport>(countPlaces, pictureWidth, pictureHeight));
        }
    }

    public Parking<ITransport> getParking(int ind)
    {
        if (ind > -1 && ind < parkingStages.size())
        {
            return parkingStages.get(ind);
        }
        return null;
    }
}
