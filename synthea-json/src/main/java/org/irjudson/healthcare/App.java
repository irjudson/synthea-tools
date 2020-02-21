package org.irjudson.healthcare;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.mitre.synthea.engine.Generator;
import org.mitre.synthea.export.Exporter;
import org.mitre.synthea.helpers.Config;

public class App {

    public static void main(String[] args) {
        // Configure options and override default file output configuration
        Generator.GeneratorOptions options = new Generator.GeneratorOptions();
        options.population = 10;
        Config.set("exporter.fhir.export", "false");
        Config.set("exporter.hospital.fhir.export", "false");
        Config.set("exporter.practitioner.fhir.export", "false");
        Exporter.ExporterRuntimeOptions ero = new Exporter.ExporterRuntimeOptions();
        ero.enableQueue(Exporter.SupportedFhirVersion.R4);

        // Create and start generator
        Generator generator = new Generator(options, ero);
        ExecutorService generatorService = Executors.newFixedThreadPool(1);
        generatorService.submit(() -> generator.run());

        // Retrieve the generated records
        int recordCount = 0;
        while (recordCount < options.population) {
            try {
                String jsonRecord = ero.getNextRecord();
                recordCount++;
                processRecord(jsonRecord);
            } catch (InterruptedException ex) {
                break;
            }
        }

        // Shutdown the generator
        generatorService.shutdownNow();
    }
    public static void processRecord(String jsonRecord)
    {
        System.out.println(jsonRecord);
    }
}