package adventofcode.day.day20;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import adventofcode.utils.Particle;
import adventofcode.utils.Vector3;

public class Day20 {

    private static final Path INPUT_PATH = Paths.get(Paths.get("").toAbsolutePath() + "/src/main/java/adventofcode/day/day20/input.txt");

    public static void main(final String[] args) throws IOException {
        starOne();
        starTwo();
    }

    private static void starOne() throws IOException {
        final List<Particle> particles = initList();
        for (int i = 0; i < 500; i++) {
            particles.forEach(Day20::updateParticle);
        }

        particles.sort(Comparator.comparingLong(a -> Math.abs(a.getP().getX()) + Math.abs(a.getP().getY()) + Math.abs(a.getP().getZ())));
        System.out.println(particles.get(0).getId());
    }

    private static void starTwo() throws IOException {
        final List<Particle> particles = initList();
        System.out.println("Start size: " + particles.size());

        for (int i = 0; i < 39; i++) {
            particles.forEach(Day20::updateParticle);

            final Set<Particle> duplicates = new HashSet<>();
            for (final Particle pva : particles) {
                final int occurrences = Collections.frequency(particles, pva);
                if (occurrences > 1) {
                    duplicates.add(pva);
                }
            }
            particles.removeAll(duplicates);
        }
        System.out.println("End size: " + particles.size());
    }

    private static void updateParticle(final Particle particle) {
        particle.getV().setX(particle.getV().getX() + particle.getA().getX());
        particle.getV().setY(particle.getV().getY() + particle.getA().getY());
        particle.getV().setZ(particle.getV().getZ() + particle.getA().getZ());

        particle.getP().setX(particle.getP().getX() + particle.getV().getX());
        particle.getP().setY(particle.getP().getY() + particle.getV().getY());
        particle.getP().setZ(particle.getP().getZ() + particle.getV().getZ());
    }

    private static List<Particle> initList() {
        final List<String> input = getInput().stream()
            .map(str -> str.replaceAll("p=", "")
                .replaceAll("v=", "")
                .replaceAll("a=", "")
                .replaceAll(" ", "")
                .replaceAll("<", "")
                .replaceAll(">,", "|")
                .replaceAll(">", ""))
            .collect(Collectors.toList());

        final List<Particle> pvaList = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            final String str = input.get(i);
            final String[] arr = str.split("\\|");
            final String[] p = arr[0].split(",");
            final String[] v = arr[1].split(",");
            final String[] a = arr[2].split(",");
            final Particle particle = new Particle();
            particle.setId(i);
            particle.setP(new Vector3(Long.parseLong(p[0]), Long.parseLong(p[1]), Long.parseLong(p[2])));
            particle.setV(new Vector3(Long.parseLong(v[0]), Long.parseLong(v[1]), Long.parseLong(v[2])));
            particle.setA(new Vector3(Long.parseLong(a[0]), Long.parseLong(a[1]), Long.parseLong(a[2])));

            pvaList.add(particle);
        }

        return pvaList;
    }

    private static List<String> getInput() {
        try {
            return Files.readAllLines(INPUT_PATH);
        } catch (final Exception e) {
            System.out.println("Error while reading file...");
        }

        return Collections.emptyList();
    }

}
