import java.io.*;
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
    /// Ширина окна отрисовки
    /// </summary>
    private int pictureWidth;
    /// <summary>
    /// Высота окна отрисовки
    /// </summary>
    private int pictureHeight;
	
	/// <summary>
    /// Конструктор
    /// </summary>
    /// <param name="countStages">Количество уровенй парковки</param>
    /// <param name="pictureWidth"></param>
    /// <param name="pictureHeight"></param>
    public MultiLevelParking(int countStages, int pictureWidth, int pictureHeight)
    {
        parkingStages = new ArrayList<Parking<ITransport>>();
        this.pictureWidth = pictureWidth;
        this.pictureHeight = pictureHeight;

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
    
    public void saveData(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            writeToFile("CountLeveles:" + parkingStages.size() + System.lineSeparator(), bw);
            for (Parking<ITransport> level : parkingStages) {
                writeToFile("Level" + System.lineSeparator(), bw);
                for (int i = 0; i < countPlaces; i++) {
                	try {
	                    ITransport tractor = level.getTrasport(i);
                        if (tractor.getClass().getSimpleName().equals("TractorBase")) {
                            writeToFile(i + ":TractorBase:", bw);
                        }
                        if (tractor.getClass().getSimpleName().equals("Tractor")) {
                            writeToFile(i + ":Tractor:", bw);
                        }
                        writeToFile(tractor + System.lineSeparator(), bw);
                	} catch (Exception ex) { }
                    finally { }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void loadData(String filename) throws Exception {
        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        String bufferTextFromFile = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                bufferTextFromFile += line + "\n";
            }
            String[] strs = bufferTextFromFile.split("\n");
            if (strs[0].contains("CountLeveles")) {
                int count = Integer.parseInt(strs[0].split(":")[1]);
            } else {
                throw new Exception("Неверный формат файла");
            }
            int counter = -1;
            ITransport tractor = null;
            for (int i = 1; i < strs.length - 1; ++i) {
                if (strs[i].equals("Level")) {
                    counter++;
                    parkingStages.set(counter, (new Parking<ITransport>(countPlaces, pictureWidth, pictureHeight)));
                    continue;
                }
                if (strs[i].isEmpty() || strs[i] == null) {
                    continue;
                }
                if (strs[i].split(":")[1].equals("TractorBase")) {
                	tractor = new TractorBase(strs[i].split(":")[2]);
                } else if (strs[i].split(":")[1].equals("Tractor")) {
                	tractor = new Tractor(strs[i].split(":")[2]);
                }
                parkingStages.get(counter).setTrasport(Integer.parseInt(strs[i].split(":")[0]), tractor);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    private void writeToFile(String text, BufferedWriter writer) {
        try {
            char[] info = text.toCharArray();
            writer.write(info, 0, info.length);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
