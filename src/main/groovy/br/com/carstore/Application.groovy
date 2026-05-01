package br.com.carstore

import io.micronaut.runtime.Micronaut
import groovy.transform.CompileStatic


@CompileStatic
class Application {

    static void main(String[] args) {
        Micronaut.run(Application, args)
    }
}
