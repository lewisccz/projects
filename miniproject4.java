// LEWIS CHARLES
// 13 DECEMBER 2021
// VERSION 3
// A chatbot procedural program that can have realistic conversations with people about a specific topic

import java.util.Scanner; // Needed to make Scanner available

class chatbot4
{
    public static void main (String [] a)
    {
        chat();
        System.exit(0);
    }

    public static void chat ()
    {
        boolean end = false;
        boolean nextquestion = true;

        final int music_array_size = 4;
        final int country_array_size = 4;
        final int no_questions = 2;

        int[] musicProbability = musicProbability(music_array_size);
        String[] musicGenres = musicGenres(music_array_size);
        String[] musicResponses = musicResponses(music_array_size);
        String[] countries = initialiseCountries(country_array_size);
        String[] country_facts = initialiseCountryFacts(country_array_size);
        String[] questions = QuestionsArray(no_questions);

        Music MusicArrays = createMusic(musicGenres, musicResponses, musicProbability);
        Location CountryArrays = createLocation(countries, country_facts);
        // creates records

        while (end == false)
        // program runs until user says goodbye
        {
            String name = greeting();

            if (checkExit(name) == true)
            {
                end = true;
            }
            else
            {
                String input = "";
                String fav_genre = fav_genre();
                orderSearch(MusicArrays, music_array_size);
                searchMusicResponse(MusicArrays, music_array_size, fav_genre);

                for (int i = 0; i < no_questions; i++)
                {
                    input = inputString(questions[i]);

                    if (input.equals("Got to go"))
                    {
                        i = no_questions;
                    }
                    else
                    {
                        processQuestion(i, input, CountryArrays, country_array_size);
                    }
                }

                goodbye(name);
            }
        }
        return;
    }

    public static String greeting ()
    // greets user and asks for their name
    {
        System.out.println("Hello I am chatbot! Type EXIT to leave the conversation");
        String input = inputString("What is your name? ");

        return input;
    }

    public static String fav_genre ()
    // asks the user for their favourite genre of music
    {
        String favourite = inputString("What is your favourite genre of music? ");
        return favourite;
    }

    public static void goodbye (String name)
    // Prints goodbye statement after the bot is done chatting
    {
        System.out.println("Well it was nice to meet you " + name + "! I have to go now, speak later!");
        return;
    }

    public static String inputString (String s)
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.println(s);
        String input = keyboard.nextLine();
        return input;
    }

    public static String[] musicGenres (int array_size)
    {
    String[] genres = new String[array_size];
    genres[0] = "Hip-Hop";
    genres[1] = "Rock";
    genres[2] = "Pop";
    genres[3] = "Country";

    return genres;
    }

    public static String[] musicResponses (int array_size)
    {
        String[] responses = new String[array_size];
        responses[0] = "That's my favourite genre too! ";
        responses[1] = "I love Rock aswell! ";
        responses[2] = "I dont listen to much pop music. ";
        responses[3] = "You are disgusting ";

        return responses;
    }

    public static int[] musicProbability (int array_size)
    {
        int[] probability = new int[array_size];
        probability[0] = 5;
        probability[1] = 3;
        probability[2] = 4;
        probability[3] = 1;

        return probability;
    }

    public static Music createMusic (String[] genres, String[] responses, int[] probability)
    // creates record for music genre
    {
        Music s = new Music();
        s.genres = genres;
        s.responses = responses;
        s.probability = probability;
        return s;
    }

    public static String[] getGenres(Music s)
    {
        return s.genres;
    }

    public static String[] getResponses(Music s)
    {
        return s.responses;
    }

    public static int[] getProbability(Music s)
    {
        return s.probability;
    }

    public static String[] initialiseCountries (int country_array_size)
    {
        String[] countries = new String[country_array_size];

        countries[0] = "Russia";
        countries[1] = "France";
        countries[2] = "Spain";
        countries[3] = "Japan";

        return countries;
    }

    public static String[] initialiseCountryFacts (int country_array_size)
    {
        String[] country_facts = new String[country_array_size];

        country_facts[0] = "You better bring a jacket! ";
        country_facts[1] = "I heard the people there are not very nice ";
        country_facts[2] = "Great choice! Its a beautiful place to visit during the Summer ";
        country_facts[3] = "Be prepared to spend a lot of money! ";

        return country_facts;
    }

    public static Location createLocation (String[] countries, String[] country_facts)
    // creates record for music genre
    {
        Location s = new Location();
        s.countries = countries;
        s.country_facts = country_facts;
        return s;
    }

    public static String[] getCountry(Location s)
    {
        return s.countries;
    }

    public static String[] getCountryFact(Location s)
    {
        return s.country_facts;
    }

    public static boolean checkExit(String input)
    // checks if the user has said goodbye to end the program
    {
        boolean exit = false;

        if (input.equals("EXIT"))
        {
            exit = true;
        }
        return exit;
    }

    public static void searchMusicResponse (Music MusicArrays, int array_size, String genre)
    // searches for a response in the records for the users favourite genre
    {
        boolean response = false;

        for (int i = 0; i < array_size; i++)
        {
            if (genre.equals(getGenres(MusicArrays)[i]))
            {
                System.out.println(getResponses(MusicArrays)[i]);
                response = true;
                i = array_size;
            }
        }

        if (response == false)
        {
            System.out.println("I dont know that genre... ");
        }

        return;
    }

    public static String[] QuestionsArray (int no_questions)
    // creates an array with random questions to ask user
    {
        String[] questions = new String[no_questions];
        questions[0] = "How old are you? ";
        questions[1] = "What is one country would you like to visit? ";

        return questions;
    }

    public static void processQuestion (int i, String response, Location CountryArrays, int country_array_size)
    {
        if (i == 0)
        {
            int age = Integer.parseInt(response);
            respondAge(age);

        }
        else if (i == 1)
        {
            String country = response;
            searchCountryResponse(country, CountryArrays, country_array_size);
        }
        else if (i == 2)
        {
            String poo = response;
        }
    }

    public static void respondAge(int age)
    {
        if (age < 21)
        {
            System.out.println("You've got a bright future ahead of you! ");
        }
        else if (age > 30)
        {
            System.out.println("Hope you've picked out a nice retirement home. ");
        }
        else
        {
            System.out.println("Have fun because its only downhill from here. ");
        }
    }

    public static void searchCountryResponse(String country, Location CountryArrays, int array_size)
    {
        boolean response = false;

        for (int i = 0; i < array_size; i++)
        {
            if (country.equals(getCountry(CountryArrays)[i]))
            {
                System.out.println(getCountryFact(CountryArrays)[i]);
                response = true;
            }
        }

        if (response == false)
        {
            System.out.println("I dont know that genre... ");
        }

        return;
    }

    public static Music orderSearch (Music s, int array_size)
    {
        for (int pass = 0; pass <= array_size - 2; pass++)
        {
            for (int pos = 0; pos <= array_size - pass - 2; pos++)
            {
                if (getProbability(s)[pos] > getProbability(s)[pos + 1])
                {
                    s = swap(s, pos, pos + 1);
                }
            }
        }
        return s;
    }

    public static Music swap (Music s, int pos0, int pos1)
    {
        int tempInt;
        String tempStr = "x";

        int[] probability = getProbability(s);
        tempInt = probability[pos0];
        probability[pos0] = probability[pos1];
        probability[pos1] = tempInt;
        s = setProbability(s, probability);

        String[] genres = getGenres(s);
        tempStr = genres[pos0];
        genres[pos0] = genres[pos1];
        genres[pos1] = tempStr;
        s = setGenres(s, genres);

        String[] responses = getResponses(s);
        tempStr = responses[pos0];
        responses[pos0] = responses[pos1];
        responses[pos1] = tempStr;
        s = setResponses(s, responses);

        return s;
    }

    public static Music setProbability (Music s, int[] newarray)
    {
        s.probability = newarray;
        return s;
    }
    public static Music setGenres (Music s, String[] newarray)
    {
        s.genres = newarray;
        return s;
    }

    public static Music setResponses (Music s, String[] newarray)
    {
        s.responses = newarray;
        return s;
    }
}

class Music
{
    String[] genres;
    String[] responses;
    int[] probability;
}

class Location
{
    String[] countries;
    String[] country_facts;
}
