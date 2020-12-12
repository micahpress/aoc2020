import dayeight.DayEightEngine
import dayeight.DayEightImporter
import dayeleven.DayElevenEngine
import dayeleven.DayElevenImporter
import dayfive.DayFiveEngine
import dayfive.DayFiveImporter
import dayfour.DayFourEngine
import dayfour.DayFourImporter
import dayone.DayOneEngine
import dayone.DayOneImporter
import dayseven.DaySevenEngine
import dayseven.DaySevenImporter
import daysix.DaySixEngine
import daysix.DaySixImporter
import daythree.DayThreeEngine
import daythree.DayThreeImporter
import daytwo.DayTwoEngine
import daytwo.DayTwoImporter

fun main(args: Array<String>) {
    dayEleven()
}

fun dayOne() {
    val importer = DayOneImporter("/home/mpress/IdeaProjects/aoc/src/main/input/dayOne.txt")
    val engine = DayOneEngine(importer)
    engine.run()
}

fun dayTwo() {
    val importer = DayTwoImporter("/home/mpress/IdeaProjects/aoc/src/main/input/dayTwo.txt")
    val engine = DayTwoEngine(importer)
    engine.run()
}

fun dayThree() {
    val importer = DayThreeImporter("/home/mpress/IdeaProjects/aoc/src/main/input/dayThree.txt")
    val engine = DayThreeEngine(importer)
    engine.run()
}

fun dayFour() {
    val importer = DayFourImporter("/home/mpress/IdeaProjects/aoc/src/main/input/dayFour.txt")
    val engine = DayFourEngine(importer)
    engine.run()
}

fun dayFive() {
    val importer = DayFiveImporter("/home/mpress/IdeaProjects/aoc/src/main/input/dayFive.txt")
    val engine = DayFiveEngine(importer)
    engine.run()
}

fun daySix() {
    val importer = DaySixImporter("/home/mpress/IdeaProjects/aoc/src/main/input/daySix.txt")
    val engine = DaySixEngine(importer)
    engine.run()
}

fun daySeven() {
    val importer = DaySevenImporter("/home/mpress/IdeaProjects/aoc/src/main/input/daySeven.txt")
    val engine = DaySevenEngine(importer)
    engine.run()
}

fun dayEight() {
    val importer = DayEightImporter("/home/mpress/IdeaProjects/aoc/src/main/input/dayEight.txt")
    val engine = DayEightEngine(importer)
    engine.run()
}

fun dayEleven() {
    val importer = DayElevenImporter("/home/mpress/IdeaProjects/aoc/src/main/input/dayEleven.txt")
    val engine = DayElevenEngine(importer)
    engine.run()
}