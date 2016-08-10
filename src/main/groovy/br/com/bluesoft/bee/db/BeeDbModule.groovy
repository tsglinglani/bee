package br.com.bluesoft.bee.db

import br.com.bluesoft.bee.service.BeeWriter


/**
 * BeeDB is the module to manage the creation and destruction of your connection dbs
 *
 * bee db:create sql-oracle - generate the sql files in oracle dialect
 * bee db:create <connection> - generate a database from a specified connection
 * bee db:drop <connection> - drop a database from a specified connection
 */
public class BeeDbModule implements BeeWriter {

    def parameterCount = [
            "create": [1, 2],
            "drop": [1, 2]
    ]

    def usage() {
        println "usage: bee db:<action> <parameters>"
        println "Actions:"
        println "         db:create sql-oracle - generate the sql files in oracle dialect"
        println "         db:create <connection> - generate a database from a specified connection"
        println "         bee db:drop <connection> - drop a database from a specified connection"
    }

    def parseOptions(options) {
        def action = options.actionName
        def arguments = options.arguments

        if(parameterCount[action] == null || parameterCount[action] == 0) {
            usage()
            System.exit 0
        }

        def min = parameterCount[action][0]
        def max = parameterCount[action][1]

        if(arguments.size > max || arguments.size < min) {
            usage()
            System.exit 0
        }

        def actionRunner = null
        switch(action) {
            case "create":
                actionRunner = new BeeDbCreateAction(options: options, out: this)
                break
            case "drop":
                actionRunner = new BeeDbDropAction(options: options, out: this)
                break
            default:
                println("invalid action!")
                usage()
                System.exit 0
        }

        return actionRunner;
    }

    def run(options) {
        def actionRunner = parseOptions(options)
        if(actionRunner)
            if(!actionRunner.run())
                System.exit(1)
    }

    @Override
    void log(String msg) {
        print(msg)
    }
}
