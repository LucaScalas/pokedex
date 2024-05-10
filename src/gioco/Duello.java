package gioco;

import allenatori.Allenatore;
import eccezioni.MortePokemonException;
import pokemon.Pokemon;
import pokemon.abilita.Lanciafiamme;
import pokemon.debolezza.DebolezzaErba;
import pokemon.tipo.Tipo;
import pokemon.tipo.TipoErba;

import javax.crypto.spec.OAEPParameterSpec;
import java.lang.reflect.Array;
import java.util.*;

public class Duello {

    // Array di allenatori che contiene le info dei giocatori partecipanti
    private Allenatore[] sfidante = new Allenatore[4];

    // Inizializzazione delle istanze dei pokemon che prenderanno parte ai duelli
    private Pokemon pokemonAllenatore1 = new Pokemon("default", new TipoErba(),1,1,new Lanciafiamme(),new DebolezzaErba()) {
        @Override
        public Pokemon evoluzione() {
            return this;
        }
    };
    private Pokemon pokemonAllenatore2 = new Pokemon("default", new TipoErba(),1,1,new Lanciafiamme(),new DebolezzaErba()) {
        @Override
        public Pokemon evoluzione() {
            return this;
        }
    };

    // Inizializzazione delle HasMap che conterranno i pokemon sconfitti per i prossimi scontri
    private HashMap<String,Pokemon> panchinaAllenatore1 = new HashMap<>();
    private HashMap<String,Pokemon> panchinaAllenatore2 = new HashMap<>();

    // Metodi
    public void startPartita(){
        // Metodo che permette d'iniziare una partita con 2 giocatori o con 1 + CPU
        System.out.println("Inserisci: 1 = modalità manuale; 2 = modalità assistita.");
        int mod;
        do {
            mod = scanInt();
            if (mod<1 || mod>2){
                System.out.println("Inserisci solamente 1 o 2");
            }
        } while (mod<1 || mod>2);
        if (mod==1){
            // Caso in cui il giocatore voglia inserire completamente da solo le informazioni
            infoNumeroGiocatoriManuale();
        }
        if (mod==2){
            // Caso in cui il giocatore voglia essere assistito nell'inserimento delle informazioni
            infoNumeroGiocatoriAssistita();
        }
    }

    // Funzioni Partita
    private void partitaManuale() {
        // Funzione che avvia la partita tra 2 giocatori in modalità manuale
        infoGiocatori(2);
        selezionePokemonAllenatore1();
        selezionePokemonAllenatore2();
        lotta();
        fineMatch();
    }
    private void partitaManualeCPU(){
        // Funzione che avvia la partita tra 1 giocatore e la CPU in modalità manuale
        infoGiocatori(1);
        selezionePokemonAllenatore1();
        selezionePokemonAllenatore2CPU();
        lottaCPU();
        fineMatchCPU();
    }
    private void partitaAssistita(){
        // Funzione che avvia la partita tra 2 giocatori in modalità assistita
        infoGiocatoriAuto(2);
        selezionePokemonAllenatore1();
        selezionePokemonAllenatore2();
        lotta();
        fineMatch();
    }
    private void partitaAssistitaCPU(){
        // Funzione che avvia la partita tra 1 giocatore e la CPU in modalità assistita
        infoGiocatoriAuto(1);
        selezionePokemonAllenatore1();
        selezionePokemonAllenatore2CPU();
        lottaCPU();
        fineMatchCPU();
    }

    // Funzioni CPU
    private void infoCPU(){
        // Funzione che crea tutte le info della CPU
        sfidante[1] = new Allenatore("CPU",1,1,6);
        sfidante[1].getPokeDex().completaCatalogo();
        infoListaLimitataPersonaleAuto(1);
        infoportfolioAuto(1);
    }
    private void selezionePokemonAllenatore2CPU(){
        // Funzione che permette alla CPU di cambiare pokemon qualora venisse sconfitto in battaglia
        ArrayList<String> catalogo = new ArrayList<>(sfidante[1].getPortfolio().keySet());
        Random random = new Random();

        String nomePokemon = catalogo.get(random.nextInt(catalogo.size()));
        pokemonAllenatore2 = sfidante[1].getPortfolio().get(nomePokemon);
        System.out.println("La CPU ha schierato: " + pokemonAllenatore2.getNome());
        sfidante[1].getPortfolio().remove(pokemonAllenatore2.getNome());
    }
    private void lottaCPU(){
        // Funzione che permette il duello tra 1 allenatore e la CPU
        int i = 0;
        int j = 0;

        do {
            // Turno del giocatore 1
            try {
                pokemonAllenatore1.attacco(pokemonAllenatore2);
                messaggioDanno1(pokemonAllenatore1, pokemonAllenatore2);
            }catch (MortePokemonException e){
                // Condizione di morte del pokemon del giocatore 2
                mortePokemonAllenatore2();
                i++;
                if (i<3) {
                    selezionePokemonAllenatore2();
                } else break;
            }

            // Turno del giocatore 2
            try {
                pokemonAllenatore2.attacco(pokemonAllenatore1);
                messaggioDanno2(pokemonAllenatore2, pokemonAllenatore1);
            }catch (MortePokemonException e){
                // Condizione di morte del pokemon del giocatore 1
                mortePokemonAllenatore1();
                j++;
                if (j<3) {
                    selezionePokemonAllenatore1();
                } else break;
            }
        } while (i<3 || j<3);

        if (i==3){
            System.out.println("HA VINTO " + sfidante[0].getNome() + "!!!");
        }
        if (j==3){
            System.out.println("HA VINTO " + sfidante[1].getNome() + "!!!");
        }
        if (panchinaAllenatore1.size()<3){
            panchinaAllenatore1.put(pokemonAllenatore1.getNome(),pokemonAllenatore1);
        }
        if (panchinaAllenatore2.size()<3){
            panchinaAllenatore2.put(pokemonAllenatore2.getNome(),pokemonAllenatore2);
        }
    }
    private void fineMatchCPU(){
        // Funzione che gestisce le varie opzioni alla fine di un match
        System.out.println("Volete rigiocare? 1=SI 2=NO");
        int input1;
        int input2;
        do {
            input1 = scanInt();
            if (input1<1 || input1>2){
                System.out.println("Inserisci solamente 1 o 2");
            }
        } while (input1<1 || input1>2);
        if (input1==1){
            System.out.println("Volete riniziare tutto da capo (1) o volete utilizzare gli stessi pokemon(2)?");
            do {
                input2 = scanInt();
                if (input2<1 || input2>2){
                    System.out.println("Inserisci solamente 1 o 2");
                }
            } while (input2<1 || input2>2);
            if (input2==1){
                // Caso di rematch inserendo tutto da capo
                partitaAssistitaCPU();
            }
            if (input2==2){
                // Caso di rematch con gli stessi pokemon
                rematchCPU();
            }
        }
        if (input1==2){
            // Caso di arresto del programma
            System.out.println("GAME OVER");
        }
    }
    private void rematchCPU(){
        // Funzione che permette il rematch con gli stessi pokemon tra 1 giocatore e la CPU

        // Transizione dei pokemon dalla panchina ai portfoli dei rispettivi giocatori
        restituzionePokemon();

        // Cura della squadra
        sfidante[0].curaTeam();
        sfidante[1].curaTeam();
        System.out.println("Le vostre squadre sono state rimesse in sesto!");

        // Inizio lotta
        selezionePokemonAllenatore1();
        selezionePokemonAllenatore2CPU();
        lottaCPU();
        fineMatchCPU();
    }

    // Set Info Giocatori mod.Manuale
    private void infoGiocatori(int giocatori){
        // Funzione che permette d' inserire tutte le informazioni dei giocatori dato il numero di questi ultimi in ingresso

        // Creazione della CPU
        infoCPU();

        // Processo di creazione dei nuovi giocatori
        for (int i=0;i<giocatori;i++){
            int numero = i+1;
            sfidante[i] = new Allenatore("default",1,1,1);
            System.out.println("Inserisci le informazioni del giocatore " + numero + ":");

            // Info nome
            infoNome(i);

            // Info età
            infoEta(i);

            // Info esperienza
            infoEsperienza(i);

            // Info numero pokemon posseduti
            infoNumeroPokemonPosseduti(i);

            // Aggiunta di tutti i pokemon esistenti al pokeDex degli allenatori
            sfidante[i].getPokeDex().completaCatalogo();

            // Creazione della lista personale
            infoListaLimitataPersonale(i);

            // Creazione portfolio
            infoPortfolio(i);

            // Selezione del pokemon preferito
            infoPokemonPreferito(i);
        }
    }
    private void infoNumeroGiocatoriManuale(){
        // Funzione che gestisce se avviare una partita con CPU o meno
        int giocatori;
        System.out.println("Inserire il numero dei giocatori (MAX 2): ");
        do {
            giocatori = scanInt();
            if (giocatori>2 || giocatori<1){
                System.out.println("Numero di giocatori non valido");
            }
        } while (giocatori>2 || giocatori<1);
        if (giocatori==1){
            System.out.println("Hai inserito: " + giocatori + " giocatore");
            // Caso CPU
            partitaManualeCPU();
        }
        else {
            System.out.println("Hai inserito: " + giocatori + " giocatori");
            // Caso 2 giocatori
            partitaManuale();
        }

    }
    private void infoNome(int i){
        // Funzione che permette d'inserire le info del nome per ciascun giocatore
        System.out.println("Inserisci il nome:");
        sfidante[i].setNome(scanString());
        System.out.println("Il nome inserito è: " + sfidante[i].getNome());
    }
    private void infoEta(int i){
        // Funzione che permette d'inserire le info dell'età per ciascun giocatore
        System.out.println("Inserisci l'età:");
        sfidante[i].setEta(scanInt());
        System.out.println("l'età inserita è: " + sfidante[i].getEta() + " anni");
    }
    private void infoEsperienza(int i){
        // Funzione che permette d'inserire le info dell'esperienza per ciascun giocatore
        System.out.println("Inserisci l'esperienza:");
        sfidante[i].setEsperienza(scanInt());
        System.out.println("L'esperienza inserita è pari a: " + sfidante[i].getEsperienza());
    }
    private void infoNumeroPokemonPosseduti(int i){
        // Funzione che permette d'inserire le info dei pokemon posseduti per ciascun giocatore
        System.out.println("Inserisci il numero di pokemon posseduti:");
        sfidante[i].setNumeroDiPokemonPosseduti(scanInt());
        System.out.println("Il numero di pokemon posseduti è: " + sfidante[i].getNumeroDiPokemonPosseduti());
    }
    private void infoListaLimitataPersonale(int i){
        // Funzione che permette d'inserire le info della lista limitata personale per ciascun giocatore
        System.out.println("Inserisci uno alla volta i nomi dei pokemon con l'iniziale maiuscola da aggiungere alla tua lista personale (MAX 6):");
        do {
            String nomePokemon = scanString();
            sfidante[i].aggiungiPokemonAListaPersonale(nomePokemon);
            System.out.println("La lista limitata personale contiene " + sfidante[i].getListaLimitataPersonale().size()
                    + " Pokemon, ne mancano altri " + (6-sfidante[i].getListaLimitataPersonale().size()) + " diversi");
        }while (sfidante[i].getListaLimitataPersonale().size()<6);
    }
    private void infoPortfolio(int i){
        // Funzione che permette d'inserire le info del portfolio per ciascun giocatore
        System.out.println("Inserisci uno alla volta i nomi dei pokemon con l'iniziale maiuscola da aggiungere al tuo portfolio(MAX 3):");
        do {
            String nomePokemon = scanString();
            sfidante[i].aggiungiPokemonAPortfolio(nomePokemon);
            System.out.println("Il portfolio contiene " + sfidante[i].getListaLimitataPersonale().size()
                    + " Pokemon, ne mancano altri " + (6-sfidante[i].getListaLimitataPersonale().size()) + " diversi");
        }while (sfidante[i].getPortfolio().size()<3);
    }
    private void infoPokemonPreferito(int i){
        // Funzione che permette d'inserire le info del pokemon preferito per ciascun giocatore
        System.out.println("Inserisci il nome del tuo pokemon preferito tra quelli del tuo portfolio" +
                " (il pokemon preferito avrà un bonus nel danno della sua abilità: " + sfidante[i].getPortfolio().keySet());
        String pokemon;
        do {
            pokemon=scanString();
        }while (!sfidante[i].getPortfolio().containsKey(pokemon));
        sfidante[i].getPortfolio().get(pokemon).getAbilita().setDanno(sfidante[i].getPortfolio().get(pokemon).getAbilita().getDanno()+10);
    }

    // Set Info Giocatori mod.Assistita
    private void infoNumeroGiocatoriAssistita(){
        // Funzione che gestisce se avviare una partita con CPU o meno
        int giocatori;
        System.out.println("Inserire il numero dei giocatori (MAX 2): ");
        do {
            giocatori = scanInt();
            if (giocatori>2 || giocatori<1){
                System.out.println("Numero di giocatori non valido");
            }
        } while (giocatori>2 || giocatori<1);
        if (giocatori==1){
            System.out.println("Hai inserito: " + giocatori + " giocatore");
            // Caso CPU
            partitaAssistitaCPU();
        }
        else {
            System.out.println("Hai inserito: " + giocatori + " giocatori");
            // Caso 2 giocatori
            partitaAssistita();
        }

    }
    private void infoGiocatoriAuto(int giocatori){
        // Funzione che permette di generare tutte le informazioni dei giocatori dato il numero di questi ultimi in ingresso

        // Creazione della CPU
        infoCPU();

        // Processo di creazione dei nuovi giocatori
        for (int i=0;i<giocatori;i++){
            int numero = i+1;
            sfidante[i] = new Allenatore("default",1,1,1);
            System.out.println("Generazione informazioni del giocatore " + numero + ":");

            // Info nome
            infoNomeAuto(i);

            // Info età
            infoEtaAuto(i);

            // Info esperienza
            infoEsperienzaAuto(i);

            // Info numero pokemon posseduti
            infoNumeroPokemonPossedutiAuto(i);

            // Aggiunta di tutti i pokemon esistenti al pokeDex degli allenatori
            sfidante[i].getPokeDex().completaCatalogo();

            // Creazione della lista personale
            infoListaLimitataPersonaleAuto(i);

            // Creazione portfolio
            infoportfolioAuto(i);

            // Selezione del pokemon preferito
            infoPokemonPreferito(i);

        }
    }
    private void infoNomeAuto(int i){
        // Funzione che permette di generare le info del nome per ciascun giocatore
        int numero = i+1;
        sfidante[i].setNome("Giocatore " + numero);
        System.out.println("Nome: " + sfidante[i].getNome());
    }
    private void infoEtaAuto(int i){
        // Funzione che permette di generare le info dell'età per ciascun giocatore

        Random random = new Random();
        int min = 6;
        int max = 80;
        int eta = random.nextInt(max - min + 1) + min;

        sfidante[i].setEta(eta);
        System.out.println("Età: " + sfidante[i].getEta() + " anni");
    }
    private void infoEsperienzaAuto(int i){
        // Funzione che permette di generare le info dell'esperienza per ciascun giocatore
        Random random = new Random();
        int min = 0;
        int max = 100;
        int exp = random.nextInt(max - min + 1) + min;

        sfidante[i].setEsperienza(exp);
        System.out.println("Esperienza: " + sfidante[i].getEsperienza());
    }
    private void infoNumeroPokemonPossedutiAuto(int i){
        // Funzione che permette di generare le info del numero di pokemon posseduti per ciascun giocatore
        Random random = new Random();
        int min = 1;
        int max = 20;
        int numeroPokemon = random.nextInt(max - min + 1) + min;

        sfidante[i].setNumeroDiPokemonPosseduti(numeroPokemon);
        System.out.println("Numero di pokemon posseduti: " + sfidante[i].getNumeroDiPokemonPosseduti());
    }
    private void infoListaLimitataPersonaleAuto(int i){
        // Funzione che permette di generare le info della lista limitata personale per ciascun giocatore
       ArrayList<String> catalogo = new ArrayList<>(sfidante[i].getPokeDex().getCatalogo().keySet());
        Random random = new Random();
        do {
            String nomePokemon = catalogo.get(random.nextInt(catalogo.size()));
            sfidante[i].aggiungiPokemonAListaPersonale(nomePokemon);
        }while (sfidante[i].getListaLimitataPersonale().size()<6);
        System.out.println("La tua lista limitata personale contiene: " + sfidante[i].getListaLimitataPersonale().keySet());
    }
    private void infoportfolioAuto(int i){
        // Funzione che permette di generare le info del portfolio per ciascun giocatore
        ArrayList<String> catalogo = new ArrayList<>(sfidante[i].getListaLimitataPersonale().keySet());
        Random random = new Random();
        do {
            String nomePokemon = catalogo.get(random.nextInt(catalogo.size()));
            sfidante[i].aggiungiPokemonAPortfolio(nomePokemon);
        }while (sfidante[i].getPortfolio().size()<3);
        System.out.println("Il tuo portfolio contiene: " + sfidante[i].getPortfolio().keySet());
    }

    // Metodi per l'esecuzione della lotta
    private void selezionePokemonAllenatore1() {
        // Funzione che permette all'allenatore 1 di scegliere o sostituire il pokemon da mandare in campo

        // Selezione del pokemon da schierare
        System.out.println("I tuoi pokemon a disposizione sono: " + sfidante[0].getPortfolio().keySet());
        System.out.println("Inserisci il nome del Pokemon da schierare dal tuo portfolio:");

        String nomePokemon;
        do {
            nomePokemon = scanString();
            pokemonAllenatore1 = sfidante[0].getPortfolio().get(nomePokemon);
            if (!sfidante[0].getPortfolio().containsKey(nomePokemon)){
                System.out.println("Il pokemon non è presente nel tuo portfolio...");
            }
        }while (!sfidante[0].getPortfolio().containsKey(nomePokemon));

        System.out.println(sfidante[0].getNome() + " ha schierato: " + getPokemonAllenatore1().getNome() + "!");
        sfidante[0].getPortfolio().remove(pokemonAllenatore1.getNome());
    }
    private void selezionePokemonAllenatore2() {
        // Funzione che permette all'allenatore 2 di scegliere o sostituire il pokemon da mandare in campo

        // Selezione del pokemon da schierare
        System.out.println("I tuoi pokemon a disposizione sono: " + sfidante[1].getPortfolio().keySet());
        System.out.println("Inserisci il nome del Pokemon da schierare dal tuo portfolio:");

        String nomePokemon;
        do {
            nomePokemon = scanString();
            pokemonAllenatore2 = (sfidante[1].getPortfolio().get(nomePokemon));
            if (!sfidante[1].getPortfolio().containsKey(nomePokemon)){
                System.out.println("Il pokemon non è presente nel tuo portfolio...");
            }
        }while (!sfidante[1].getPortfolio().containsKey(nomePokemon));

        System.out.println(sfidante[1].getNome() + " ha schierato: " + getPokemonAllenatore2().getNome() + "!");
        sfidante[1].getPortfolio().remove(pokemonAllenatore1.getNome());
    }
    private void messaggioDanno1(Pokemon attaccante, Pokemon difensore){
        // Funzione che permette di stampare l'output del danno del pokemon dell'allenatore 1

        int danno;

        // Caso in cui l'attacco sia superefficace
        if (difensore.getDebolezza().getNome().equals(attaccante.getAbilita().getTipo().getNome())){
            danno = attaccante.getAbilita().getDanno()*2*(int)(attaccante.getEsperienza()/5.5F);
            System.out.println(attaccante.getNome() + " di " + sfidante[0].getNome() + " usa " + attaccante.getAbilita().getNome() + "!");
            System.out.println("È superefficace!!! L'attacco ha tolto al " + difensore.getNome() + " avversario "
                    + danno + " PV, lasciandolo con " + difensore.getPuntiVita() + "PV!");
        }

        // Caso in cui l'attacco sia poco efficace
        if (!difensore.getDebolezza().getNome().equals(attaccante.getAbilita().getTipo().getNome()) && difensore.getTipo().getNome().equals(attaccante.getAbilita().getTipo().getNome())){
            danno = attaccante.getAbilita().getDanno()/2*(int)(attaccante.getEsperienza()/5.5F);
            System.out.println(attaccante.getNome() + " di " + sfidante[0].getNome() + " usa " + attaccante.getAbilita().getNome() + "!");
            System.out.println("È poco efficace... L'attacco ha tolto al " + difensore.getNome() + " avversario "
                    + danno + " PV, lasciandolo con " + difensore.getPuntiVita() + "PV!");
        }

        // Caso in cui l'attacco sia normale
        if (!difensore.getDebolezza().getNome().equals(attaccante.getAbilita().getTipo().getNome()) && !difensore.getTipo().getNome().equals(attaccante.getAbilita().getTipo().getNome())){
            danno = attaccante.getAbilita().getDanno()*(int)(attaccante.getEsperienza()/5.5F);
            System.out.println(attaccante.getNome() + " di " + sfidante[0].getNome() + " usa " + attaccante.getAbilita().getNome() + "!");
            System.out.println("L'attacco ha tolto al " + difensore.getNome() + " avversario "
                    + danno + " PV, lasciandolo con " + difensore.getPuntiVita() + "PV!");
        }
    }
    private void messaggioDanno2(Pokemon attaccante, Pokemon difensore){
        // Funzione che permette di stampare l'output del danno del pokemon dell'allenatore 2

        int danno;

        // Caso in cui l'attacco sia superefficace
        if (difensore.getDebolezza().getNome().equals(attaccante.getAbilita().getTipo().getNome())){
            danno = attaccante.getAbilita().getDanno()*2*(int)(attaccante.getEsperienza()/5.5F);
            System.out.println(attaccante.getNome() + " di " + sfidante[1].getNome() + " usa " + attaccante.getAbilita().getNome() + "!");
            System.out.println("È superefficace!!! L'attacco ha tolto al " + difensore.getNome() + " avversario "
                    + danno + " PV, lasciandolo con " + difensore.getPuntiVita() + "PV!");
        }

        // Caso in cui l'attacco sia poco efficace
        if (!difensore.getDebolezza().getNome().equals(attaccante.getAbilita().getTipo().getNome()) && difensore.getTipo().getNome().equals(attaccante.getAbilita().getTipo().getNome())){
            danno = attaccante.getAbilita().getDanno()/2*(int)(attaccante.getEsperienza()/5.5F);
            System.out.println(attaccante.getNome() + " di " + sfidante[1].getNome() + " usa " + attaccante.getAbilita().getNome() + "!");
            System.out.println("È poco efficace... L'attacco ha tolto al " + difensore.getNome() + " avversario "
                    + danno + " PV, lasciandolo con " + difensore.getPuntiVita() + "PV!");
        }

        // Caso in cui l'attacco sia normale
        if (!difensore.getDebolezza().getNome().equals(attaccante.getAbilita().getTipo().getNome()) && !difensore.getTipo().getNome().equals(attaccante.getAbilita().getTipo().getNome())){
            danno = attaccante.getAbilita().getDanno()*(int)(attaccante.getEsperienza()/5.5F);
            System.out.println(attaccante.getNome() + " di " + sfidante[1].getNome() + " usa " + attaccante.getAbilita().getNome() + "!");
            System.out.println("L'attacco ha tolto al " + difensore.getNome() + " avversario "
                    + danno + " PV, lasciandolo con " + difensore.getPuntiVita() + "PV!");
        }
    }
    private void mortePokemonAllenatore2(){
        // Funzione che concatena le ricompense per il pokemon dell'allenatore 1 se sconfigge il pokemon dell'allenatore 2

        int newExp = 50;
        System.out.println("Il " + pokemonAllenatore2.getNome() + " di " + sfidante[1].getNome() + " è stato sconfitto!");
        panchinaAllenatore2.put(pokemonAllenatore2.getNome(), pokemonAllenatore2);
        sfidante[0].setEsperienza(sfidante[0].getEsperienza() + newExp);
        pokemonAllenatore1.setEsperienza(pokemonAllenatore1.getEsperienza() + newExp);
        System.out.println(pokemonAllenatore1.getNome() + " ha guadagnato " + newExp + " punti esperienza (" + pokemonAllenatore1.getEsperienza() + " TOT)!");
        pokemonAllenatore1 = pokemonAllenatore1.evoluzione();
    }
    private void mortePokemonAllenatore1(){
        // Funzione che concatena le ricompense per il pokemon dell'allenatore 2 se sconfigge il pokemon dell'allenatore 1

        int newExp = 50;
        System.out.println("Il " + pokemonAllenatore1.getNome() + " di " + sfidante[0].getNome() + " è stato sconfitto!");
        panchinaAllenatore1.put(pokemonAllenatore1.getNome(), pokemonAllenatore1);
        sfidante[1].setEsperienza(sfidante[1].getEsperienza() + newExp);
        pokemonAllenatore2.setEsperienza(pokemonAllenatore2.getEsperienza() + newExp);
        System.out.println(pokemonAllenatore2.getNome() + " ha guadagnato " + newExp + " punti esperienza (" + pokemonAllenatore2.getEsperienza() + " TOT)!");
        pokemonAllenatore2 = pokemonAllenatore2.evoluzione();
    }
    private void restituzionePokemon(){
        // Restituisce i pokemon delle panchine ai portfolio dei rispettivi allenatori

        // Creazione degli array contenenti le chiavi
        String[] chiavi1 = panchinaAllenatore1.keySet().toArray(new String[3]);
        String[] chiavi2 = panchinaAllenatore2.keySet().toArray(new String[3]);

        // Aggiunta del Pokemon iesimo dell'allenatore 1 al suo portfolio e rimozione dalla panchina
        int i = 0;
        if (sfidante[0].getPortfolio().size()<3) {
            do {
                sfidante[0].getPortfolio().put(panchinaAllenatore1.get(chiavi1[i]).getNome(), panchinaAllenatore1.get(chiavi1[i]));
                panchinaAllenatore1.remove(chiavi1[i]);
                i++;
            } while (sfidante[0].getPortfolio().size() < 3);
        }

        // Aggiunta del Pokemon iesimo dell'allenatore 2 al suo portfolio e rimozione dalla panchina
        int j = 0;
        if (sfidante[1].getPortfolio().size()<3) {
            do {
                sfidante[1].getPortfolio().put(panchinaAllenatore2.get(chiavi2[j]).getNome(), panchinaAllenatore2.get(chiavi2[j]));
                panchinaAllenatore2.remove(chiavi2[j]);
                j++;
            } while (sfidante[1].getPortfolio().size() < 3);
        }
    }
    private void lotta(){
        // Funzione che permette di creare un duello tra 2 giocatori
        int i = 0;
        int j = 0;

        do {
            // Turno del giocatore 1
            try {
                pokemonAllenatore1.attacco(pokemonAllenatore2);
                messaggioDanno1(pokemonAllenatore1, pokemonAllenatore2);
            }catch (MortePokemonException e){
                // Condizione di morte del pokemon del giocatore 2
                mortePokemonAllenatore2();
                i++;
                if (i<3) {
                    selezionePokemonAllenatore2();
                } else break;
            }

            // Turno del giocatore 2
            try {
                pokemonAllenatore2.attacco(pokemonAllenatore1);
                messaggioDanno2(pokemonAllenatore2, pokemonAllenatore1);
            }catch (MortePokemonException e){
                // Condizione di morte del pokemon del giocatore 1
                mortePokemonAllenatore1();
                j++;
                if (j<3) {
                    selezionePokemonAllenatore1();
                } else break;
            }
        } while (i<3 || j<3);

        if (i==3){
            System.out.println("HA VINTO " + sfidante[0].getNome() + "!!!");
        }
        if (j==3){
            System.out.println("HA VINTO " + sfidante[1].getNome() + "!!!");
        }
        if (panchinaAllenatore1.size()<3){
            panchinaAllenatore1.put(pokemonAllenatore1.getNome(),pokemonAllenatore1);
        }
        if (panchinaAllenatore2.size()<3){
            panchinaAllenatore2.put(pokemonAllenatore2.getNome(),pokemonAllenatore2);
        }
    }
    private void fineMatch(){
        // Funzione che gestisce la fine di un match
        System.out.println("Volete rigiocare? 1=SI 2=NO");
        int input1;
        int input2;
        do {
            input1 = scanInt();
            if (input1<1 || input1>2){
                System.out.println("Inserisci solamente 1 o 2");
            }
        } while (input1<1 || input1>2);
        if (input1==1){
            System.out.println("Volete riniziare tutto da capo (1) o volete utilizzare gli stessi pokemon(2)?");
            do {
                input2 = scanInt();
                if (input2<1 || input2>2){
                    System.out.println("Inserisci solamente 1 o 2");
                }
            } while (input2<1 || input2>2);
            if (input2==1){
                // Caso di rematch generando tutto da capo
                partitaAssistita();
            }
            if (input2==2){
                // Caso di rematch con gli stessi pokemon
                rematch();
            }
        }
        if (input1==2){
            // Caso di arresto del programma
            System.out.println("GAME OVER");
        }
    }
    private void rematch(){
        // Funzione che gestisce il rematch mantendendo gli stessi pokemon utilizzati precedentemente

        // Transizione dei pokemon dalla panchina ai portfoli dei rispettivi giocatori
        restituzionePokemon();

        // Cura della squadra
        sfidante[0].curaTeam();
        sfidante[1].curaTeam();
        System.out.println("Le vostre squadre sono state rimesse in sesto!");

        // Inizio lotta
        selezionePokemonAllenatore1();
        selezionePokemonAllenatore2();
        lotta();
        fineMatch();
    }

    // Funzioni Scanner
    public static String scanString(){
        Scanner myScanner = new Scanner(System.in);
        String myString = "";
        do {
            if (myScanner.hasNext()){
                myString = myScanner.nextLine();
            }
            if (!myString.matches("[a-zA-Z]+")){
                System.out.println("inserisci solo lettere senza spazi");
            }
        } while (!myString.matches("[a-zA-Z]+"));
        return myString;
    }
    public static int scanInt(){
        Scanner myScanner = new Scanner(System.in);
        int myInt;
        String myString = "";
        do {
            if (myScanner.hasNext()){
                myString = (myScanner.next());
            }
            if (myString.matches("[a-zA-Z]+")) {
                System.out.println("inserisci solo numeri senza spazi");
            }
        } while (myString.matches("[a-zA-Z]+"));
        myInt = Integer.parseInt(myString);

        return myInt;
    }

    // Getter e Setter
    public Allenatore[] getSfidante() {
        return sfidante;
    }
    public void setSfidante(Allenatore[] sfidante) {
        this.sfidante = sfidante;
    }
    public Pokemon getPokemonAllenatore1() {
        return pokemonAllenatore1;
    }
    public void setPokemonAllenatore1(Pokemon pokemonAllenatore1) {
        this.pokemonAllenatore1 = pokemonAllenatore1;
    }
    public Pokemon getPokemonAllenatore2() {
        return pokemonAllenatore2;
    }
    public void setPokemonAllenatore2(Pokemon pokemonAllenatore2) {
        this.pokemonAllenatore2 = pokemonAllenatore2;
    }
    public HashMap<String, Pokemon> getPanchinaAllenatore1() {
        return panchinaAllenatore1;
    }
    public void setPanchinaAllenatore1(HashMap<String, Pokemon> panchinaAllenatore1) {
        this.panchinaAllenatore1 = panchinaAllenatore1;
    }
    public HashMap<String, Pokemon> getPanchinaAllenatore2() {
        return panchinaAllenatore2;
    }
    public void setPanchinaAllenatore2(HashMap<String, Pokemon> panchinaAllenatore2) {
        this.panchinaAllenatore2 = panchinaAllenatore2;
    }
}