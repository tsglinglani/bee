package br.com.bluesoft.bee.db

import br.com.bluesoft.bee.model.Options
import br.com.bluesoft.bee.service.BeeWriter
import sun.reflect.generics.reflectiveObjects.NotImplementedException

public class BeeDbCreateAction {
    Options options
    BeeWriter out

    def run() {
        def executionOption = options.arguments[0]

        switch (executionOption) {
            case "sql-oracle":

                break
            default:
                throw new NotImplementedException()
        }
    }
}
