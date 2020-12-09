package dayseven

import base.BaseEngine
import base.BaseImporter
import data.DataStrStr
import java.util.*
import kotlin.NoSuchElementException
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class DaySevenEngine(
    importer: BaseImporter<DataStrStr>
) : BaseEngine<DataStrStr>(importer) {
    private val bagLegend: HashMap<String, Bag> = HashMap()

    init {
        val input = importer.getLines()
        input.forEach { data ->
            val containerBagStr = data.str1
            val containerBag = bagLegend.getOrPut(containerBagStr, { -> Bag(containerBagStr) })

            val containedBagsStr = data.str2
            if (containedBagsStr != "no other bags") {
                val containedBagsList = containedBagsStr.split(", ")
                containedBagsList.forEach { bagInfo ->
                    val splitBagInfo = bagInfo.split(" ")
                    val quant = splitBagInfo[0].toInt()
                    val descriptor = splitBagInfo[1] + " " + splitBagInfo[2]
                    val containedBag = bagLegend.getOrPut(descriptor, { -> Bag(descriptor) })

                    containedBag.addContainer(containerBag.descriptor)
                    containerBag.addContained(containedBag.descriptor, quant)
                }
            }
        }
    }

    override fun run() {
        val numContainers = findContainingBags("shiny gold").size
        println("There are $numContainers kinds of bags that can hold a shiny gold bag.")
        println("My shiny gold bag needs to have ${countContainedBags("shiny gold")} bags in it.")
    }

    private fun findContainingBags(descriptor: String): HashSet<String> {
        val possibleParents = HashSet<String>()
        val visited = HashSet<String>()
        val toVisit: Stack<String> = Stack<String>()
        val startBag = bagLegend.getOrElse(descriptor, { -> throw NoSuchElementException() })

        startBag.getContainers().forEach { adj -> toVisit.add(adj) }

        while (!(toVisit.empty())) {
            val currBagDesc = toVisit.pop()
            possibleParents.add(currBagDesc)
            visited.add(currBagDesc)
            val newParents = bagLegend.getOrElse(currBagDesc, { -> throw NoSuchElementException() }).getContainers()
            toVisit.addAll(newParents.subtract(visited))
        }

        return possibleParents
    }

    private fun countContainedBags(descriptor: String): Int {
        val currBag = bagLegend.getOrElse(descriptor, { -> throw NoSuchElementException() })
        val containedBags = currBag.getContained()

        return if (containedBags.size == 0) {
            0
        } else {
            var totalInside = 0
            for (infoPair in containedBags) {
                totalInside += infoPair.second + infoPair.second * countContainedBags(infoPair.first)
            }
            totalInside
        }
    }
}