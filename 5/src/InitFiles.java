import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class InitFiles {
    public static void main(String[] args) {
        ArrayList<String> students = new ArrayList<>();
        students.add("Шабунин Геннадий Гаврилевич");
        students.add("Илюшина Инга Алексеевна");
        students.add("Коврова Фаина Семеновна");
        students.add("Халтурин Всеслав Остапович");
        students.add("Сальников Терентий Эрнстович");
        students.add("Аслаханов Трофим Архипович");
        students.add("Ермилова Альбина Мироновна");
        students.add("Колотушкина Марина Казимировна");
        students.add("Юнге Андриян Мирославович 	");
        students.add("Чирков Никита Михеевич 	");
        students.add("Адаксин Прохор Михеевич 	");
        students.add("Куликов Кондратий Филимонович 	");
        students.add("Арданкин Никита Самсонович 	");
        students.add("Чечёткин Иосиф Никанорович 	");
        students.add("Жгулёв Аким Вячеславович 	");
        students.add("Макаров Кондрат Артемович 	");
        students.add("Бузыцков Максим Артемиевич 	");
        students.add("Набойщиков Артём Изяславович 	");
        students.add("Торсунов Матвей Арсениевич 	");
        students.add("Чичеров Игнатий Тарасович 	");
        students.add("Шапиро Софья Петровна 	");
        students.add("Зууфин Артём Матвеевич 	");
        students.add("Горюнова Светлана Олеговна 	");
        students.add("Турчанинова Ирина Кузьмевна 	");
        students.add("Яговкин Фадей Аникитевич 	");
        students.add("Батурина Эмма Леонидовна 	");
        students.add("Акулиничев Николай Эрнестович 	");
        students.add("Чепурин Рубен Тарасович 	");
        students.add("Николаенко Дмитрий Саввевич 	");
        students.add("Бесфамильный Потап Игнатиевич 	");
        students.add("Элиашева Христина Александровна 	");
        students.add("Ростова Ева Степановна 	");
        students.add("Бухаров Сергей Давидович 	");
        students.add("Рябоконь Аркадий Олегович 	");
        students.add("Кошкова Майя Емельяновна 	");
        students.add("Яблоков Владилен Миронович 	");
        students.add("Лутугин Фома Венедиктович 	");
        students.add("Аршавин Кузьма Агапович 	");
        students.add("Ханыков Евграф Никанорович 	");
        students.add("Убыш Соломон Леонидович 	");
        students.add("Желваков Даниил Валериевич 	");
        students.add("Бердяева Юнона Иосифовна 	");
        students.add("Сподарева Влада Родионовна 	");
        students.add("Мишина Алина Андрияновна 	");
        students.add("Колесникова Злата Леонидовна 	");
        students.add("Шорин Артем Егорович 	");
        students.add("Купревич Иосиф Савелиевич 	");
        students.add("Клименко Кондратий Сидорович 	");
        students.add("Сиякаев Аскольд Измаилович 	");
        students.add("Ярыкина Агата Анатолиевна 	");
        students.add("Бухарова Ефросиния Святославовна 	");
        students.add("Тычинин Адриан Ипполитович 	");
        students.add("Минкина Анастасия Фомевна 	");
        students.add("Щеглов Аскольд Сергеевич 	");
        students.add("Желвакова Ариадна Германовна 	");
        students.add("Кузьмич Фадей Филиппович 	");
        students.add("Шибалкина Яна Феликсовна 	");
        students.add("Ручкин Иван Тарасович 	");
        students.add("Экономова Розалия Андрияновна 	");
        students.add("Осина Полина Филипповна 	");
        students.add("Смолина Маргарита Афанасиевна 	");
        students.add("Курбатов Эммануил Давидович 	");
        students.add("Пишенина Елизавета Ростиславовна 	");
        students.add("Ядыкин Вацлав Брониславович 	");
        students.add("Явлюхин Назар Елисеевич 	");
        students.add("Шилова Стела Ираклиевна 	");
        students.add("Горемыкин Трофим Федотович 	");
        students.add("Машарина Рада Несторовна 	");
        students.add("Табернакулова Инесса Тимофеевна 	");
        students.add("Шентеряков Андрон Евгениевич 	");
        students.add("Кораблёв Эдуард Ульянович 	");
        students.add("Клюкин Остап Чеславович 	");
        students.add("Бойкова Лидия Фомевна 	");
        students.add("Жарыхин Николай Савелиевич 	");
        students.add("Емельянова Влада Потаповна 	");
        students.add("Мухов Якуб Семенович 	");
        students.add("Осенныха Изольда Геннадиевна 	");
        students.add("Михно Юлиан Федосиевич 	");
        students.add("Гурский Виталий Давыдович 	");
        students.add("Чужинова Варвара Казимировна 	");
        students.add("Лёвкин Федор Климентович 	");
        students.add("Бобров Мирон Родионович 	");
        students.add("Яресько Казимир Артемович 	");
        students.add("Тихвинский Павел Капитонович 	");
        students.add("Гнусарева Варвара Андрияновна 	");
        students.add("Горелова Наталия Филипповна 	");
        students.add("Савкина Агния Феликсовна 	");
        students.add("Русинов Самсон Игнатиевич 	");
        students.add("Ягода Мария Елизаровна 	");
        students.add("Кадетова Регина Несторовна 	");
        students.add("Улицкий Адам Георгиевич 	");
        students.add("Кувардина Екатерина Алексеевна 	");
        students.add("Гершкович Давид Владимирович 	");
        students.add("Щеткин Моисей Назарович 	");
        students.add("Елисеева Эмилия Алексеевна 	");
        students.add("Некрестьянов Порфирий Климентович 	");
        students.add("Костомаров Бронислав Якубович 	");
        students.add("Янечко Мир Демьянович");
        students.add("Синицын Ростислав Михаилович");
        students.add("Шихина Юлия Георгиевна");

        ArrayList<String> groups = new ArrayList<>();
        groups.add("101");
        groups.add("102");
        groups.add("103");
        groups.add("104");
        groups.add("105");
        groups.add("106");
        groups.add("107");

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("FIO"))) {
            for (int i = 0; i < students.size(); i++) {
                writer.write(Integer.toString(i));
                writer.write(" ");
                String[] studentName = students.get(i).split(" ");
                writer.write(studentName[0] + " " + studentName[1] + " " + studentName[2]);
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Groups"))) {
            Random rnd = new Random();
            for (int i = 0; i < students.size(); i++) {
                int groupId = rnd.nextInt(groups.size());
                writer.write(Integer.toString(groupId));
                writer.write(" ");
                writer.write(groups.get(groupId));
                writer.write(" ");
                writer.write(Integer.toString(i));
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> subjects = new ArrayList<>();
        subjects.add("Chemistry");
        subjects.add("Math");
        subjects.add("Physics");
        subjects.add("Literature");
        subjects.add("CS");
        subjects.add("Algebra");
        subjects.add("Biology");

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Subjects"))) {

            for (int i = 0; i < subjects.size(); i++) {
               writer.write(Integer.toString(i));
               writer.write(" ");
               writer.write(subjects.get(i));
               writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> dates = new ArrayList<>();
        dates.add("1.07.2030");
        dates.add("2.07.2030");
        dates.add("4.07.2030");
        dates.add("7.07.2030");
        dates.add("10.08.2030");
        dates.add("12.08.2030");
        dates.add("14.08.2030");


        try(BufferedWriter writer = new BufferedWriter(new FileWriter("Marks"))) {
            Random rnd = new Random();
            for (int i = 0; i < students.size(); i++) {
                for (int j = 0; j < subjects.size(); j++) {

                    writer.write(Integer.toString(j));
                    writer.write(" ");
                    writer.write(Integer.toString(i));
                    writer.write(" ");
                    writer.write(Integer.toString(rnd.nextInt(5) + 2));
                    writer.write(" ");
                    writer.write(dates.get(rnd.nextInt(dates.size())));
                    writer.newLine();
                }

            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
