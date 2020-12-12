import dayeight.DayEightEngine
import dayeight.DayEightImporter
import dayfive.DayFiveEngine
import dayfive.DayFiveImporter
import dayfour.DayFourEngine
import dayfour.DayFourImporter
import daynine.DayNineEngine
import daynine.DayNineImporter
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
    dayNine()
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

fun dayNine() {
    val importer = DayNineImporter("/home/mpress/IdeaProjects/aoc/src/main/input/dayNine.txt")
    val engine = DayNineEngine(importer)
    engine.run()
}