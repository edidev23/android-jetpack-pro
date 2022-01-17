package com.edisiswanto.moviecatalogue.utils

import com.edisiswanto.moviecatalogue.data.source.local.entity.MovieEntity
import com.edisiswanto.moviecatalogue.data.source.local.entity.TvEntity
import com.edisiswanto.moviecatalogue.data.source.remote.response.MovieDiscover
import com.edisiswanto.moviecatalogue.data.source.remote.response.TvDiscover

object DataDummy {
    fun generateDummyMovies(): List<MovieEntity> {

        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                634649,
                "Spider-Man: No Way Home",
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                "30-09-2021",
                8.4,
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg"
            )
        )

        movies.add(
            MovieEntity(
                512195,
                "Red Notice",
                "An Interpol-issued Red Notice is a global alert to hunt and capture the world's most wanted. But when a daring heist brings together the FBI's top profiler and two rival criminals, there's no telling what will happen.",
                "04-11-2021",
                6.8,
                "R.drawable.rednotice"
            )
        )

        movies.add(
            MovieEntity(
                634649,
                "Spider-Man: No Way Home",
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                "15-12-2021",
                8.4,
                "R.drawable.spiderman"
            )
        )

        movies.add(
            MovieEntity(
                585245,
                "Clifford the Big Red Dog",
                "As Emily struggles to fit in at home and at school, she discovers a small red puppy who is destined to become her best friend. When Clifford magically undergoes one heck of a growth spurt, becomes a gigantic dog and attracts the attention of a genetics company, Emily and her Uncle Casey have to fight the forces of greed as they go on the run across New York City. Along the way, Clifford affects the lives of everyone around him and teaches Emily and her uncle the true meaning of acceptance and unconditional love.",
                "10-11-2021",
                7.6,
                "R.drawable.clifford"
            )
        )

        movies.add(
            MovieEntity(
                566525,
                "Shang-Chi and the Legend of the Ten Rings",
                "Shang-Chi must confront the past he thought he left behind when he is drawn into the web of the mysterious Ten Rings organization.",
                "01-09-2021",
                7.8,
                "R.drawable.shangchi"
            )
        )

        movies.add(
            MovieEntity(
                568124,
                "Encanto",
                "The tale of an extraordinary family, the Madrigals, who live hidden in the mountains of Colombia, in a magical house, in a vibrant town, in a wondrous, charmed place called an Encanto. The magic of the Encanto has blessed every child in the family with a unique gift from super strength to the power to heal—every child except one, Mirabel. But when she discovers that the magic surrounding the Encanto is in danger, Mirabel decides that she, the only ordinary Madrigal, might just be her exceptional family's last hope.",
                "24-11-2021",
                7.4,
                "R.drawable.encanto"
            )
        )

        movies.add(
            MovieEntity(
                617653,
                "The Last Duel",
                "King Charles VI declares that Knight Jean de Carrouges settle his dispute with his squire, Jacques Le Gris, by challenging him to a duel.",
                "13-10-2021",
                7.6,
                "R.drawable.thelastduel"
            )
        )

        movies.add(
            MovieEntity(
                524434,
                "Eternals",
                "The Eternals are a team of ancient aliens who have been living on Earth in secret for thousands of years. When an unexpected tragedy forces them out of the shadows, they are forced to reunite against mankind’s most ancient enemy, the Deviants.",
                "03-11-2021",
                7.1,
                "R.drawable.eternals"
            )
        )

        movies.add(
            MovieEntity(
                482321,
                "Spider-Man: Far From Home",
                "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
                "28-06-2019",
                7.5,
                "R.drawable.spiderman_far_from_home"
            )
        )

        movies.add(
            MovieEntity(
                609972,
                "Spider-Man: Homecoming",
                "Following the events of Captain America: Civil War, Peter Parker, with the help of his mentor Tony Stark, tries to balance his life as an ordinary high school student in Queens, New York City, with fighting crime as his superhero alter ego Spider-Man as a new threat, the Vulture, emerges.",
                "05-07-2017",
                7.4,
                "R.drawable.spiderman_home"
            )
        )

        return movies
    }

    fun generateDummyTv(): List<TvEntity> {

        val tvItem = ArrayList<TvEntity>()

        tvItem.add(
            TvEntity(
                2,
                "Naruto Shippuden the Movie: The Will of Fire",
                "Ninjas with bloodline limits begin disappearing in all the countries and blame points toward the fire nation. By Tsunade's order, Kakashi is sacrificed to prevent an all out war. After inheriting charms left by Kakashi, Naruto fights through friends and foes to prevent his death while changing the minds of those who've inherited the will of fire.",
                "01-08-2009",
                7.2,
                "R.drawable.naruto"
            )
        )

        tvItem.add(
            TvEntity(
                1,
                "Attack On Titan",
                "Several hundred years ago, humans were nearly exterminated by Titans. Titans are typically several stories tall, seem to have no intelligence, devour human beings and, worst of all, seem to do it for the pleasure rather than as a food source. A small percentage of humanity survived by walling themselves in a city protected by extremely high walls, even taller than the biggest Titans. Flash forward to the present and the city has not seen a Titan in over 100 years. Teenage boy Eren and his foster sister Mikasa witness something horrific as the city walls are destroyed by a Colossal Titan that appears out of thin air. As the smaller Titans flood the city, the two kids watch in horror as their mother is eaten alive. Eren vows that he will murder every single Titan and take revenge for all of mankind.",
                "12-10-2013",
                8.6,
                "R.drawable.attackontitan"
            )
        )

        tvItem.add(
            TvEntity(
                6,
                "Jujutsu Kaisen",
                "Yuji Itadori is a boy with tremendous physical strength, though he lives a completely ordinary high school life. One day, to save a classmate who has been attacked by curses, he eats the finger of Ryomen Sukuna, taking the curse into his own soul. From then on, he shares one body with Ryomen Sukuna. Guided by the most powerful of sorcerers, Satoru Gojo, Itadori is admitted to Tokyo Jujutsu High School, an organization that fights the curses... and thus begins the heroic tale of a boy who became a curse to exorcise a curse, a life from which he could never turn back.",
                "01-01-2020",
                8.5,
                "R.drawable.jujutsu"
            )
        )

        tvItem.add(
            TvEntity(
                3,
                "Demon Slayer: Kimetsu no Yaiba",
                "It is the Taisho Period in Japan. Tanjiro, a kindhearted boy who sells charcoal for a living, finds his family slaughtered by a demon. To make matters worse, his younger sister Nezuko, the sole survivor, has been transformed into a demon herself. Though devastated by this grim reality, Tanjiro resolves to become a “demon slayer” so that he can turn his sister back into a human, and kill the demon that massacred his family.",
                "01-01-2019",
                8.8,
                "R.drawable.kimetsu"
            )
        )

        tvItem.add(
            TvEntity(
                9,
                "Vinland Saga",
                "For a thousand years, the Vikings have made quite a name and reputation for themselves as the strongest families with a thirst for violence. Thorfinn, the son of one of the Vikings' greatest warriors, spends his boyhood in a battlefield enhancing his skills in his adventure to redeem his most-desired revenge after his father was murdered.",
                "01-01-2019",
                8.7,
                "R.drawable.vinland"
            )
        )

        tvItem.add(
            TvEntity(
                10,
                "That Time I Got Reincarnated as a Slime",
                "37-year-old corporate worker Mikami Satoru is stabbed by a random killer, and is reborn to an alternate world. But he turns out to be reborn a slime! Thrown into this new world with the name Rimuru Tempest, he begins his quest to create a world that’s welcoming to all races. Broken free from ordinary, stale past life, his fresh adventure in a fantasy world as a slime monster with unique abilities begins.",
                "01-01-2018",
                8.8,
                "R.drawable.tensei"
            )
        )

        tvItem.add(
            TvEntity(
                5,
                "Kuroko's Basketball",
                "In the story, Kagami Taiga has just enrolled into Seirin High School when he meets Kuroko Tetsuya of the school's basketball team. Kuroko happens to be the shadowy sixth member of the legendary Generation of Miracles basketball team. Together, Kagami and Kuroko aim to take their team to the inter-high school championship - against Kuroko's former teammates.",
                "01-01-2012",
                8.7,
                "R.drawable.kuroko"
            )
        )

        tvItem.add(
            TvEntity(
                4,
                "86: Eighty-Six",
                "Called “Juggernaut,” these are the unmanned combat drones developed by the Republic of San Magnolia in answer to the attacks by the autonomous unmanned drones of the neighboring Empire of Giad, the “Legion”. But they’re only unmanned in name. In reality, they are piloted by the Eighty-sixers—those considered to be less than human and treated as mere tools. Determined to achieve his own mysterious ends, Shin, the captain of Spearhead Squadron, which is comprised of Eighty-sixers, continues to fight a hopeless war on a battlefield where only death awaits him.",
                "01-01-2021",
                8.0,
                "R.drawable.delapanenam"
            )
        )

        tvItem.add(
            TvEntity(
                7,
                "The Promised Neverland",
                "Surrounded by a forest and a gated entrance, the Grace Field House is inhabited by orphans happily living together as one big family, looked after by their Mama, Isabella. Although they are required to take tests daily, the children are free to spend their time as they see fit, usually playing outside, as long as they do not venture too far from the orphanage — a rule they are expected to follow no matter what. However, all good times must come to an end, as every few months, a child is adopted and sent to live with their new family... never to be heard from again.",
                "01-01-2019",
                8.9,
                "R.drawable.anakternak"
            )
        )

        tvItem.add(
            TvEntity(
                8,
                "Mushoku Tensei: Jobless Reincarnation",
                "34-year-old virgin loser is kicked out of his home by his family and realized that his life is completely over. As he regrets wasting his life, a truck runs him over and he died. When he wakes up... He’s in a world of sword and sorcery! Reborn as a baby named Rudeus, he decides that this time, he’ll live a life he won’t regret!",
                "01-01-2021",
                8.0,
                "R.drawable.mushoku"
            )
        )

        return tvItem
    }

}