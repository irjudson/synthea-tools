package org.irjudson.healthcare;

import org.mitre.synthea.engine.Generator;
import org.mitre.synthea.helpers.Config;

public class App {

    public static void main(String[] args) {
        Generator.GeneratorOptions options = new Generator.GeneratorOptions();
        options.population = 10;
        Config.set("exporter.hospital.fhir.export", "false");
        Config.set("exporter.practitioner.fhir.export", "false");
        Generator generator = new Generator(options);
        generator.run();
    }
    
}