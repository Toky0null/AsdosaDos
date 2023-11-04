
package model;

import controller.Controller;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author tokyo
 */
public class Model {
    private int difficultyLevel = 4; // Comienza con 3 paneles
    private Random random = new Random();       
    private Timer updateTimer; // Timer para actualizar los paneles
    private int updateInterval = 3000; // Intervalo de actualización inicial de 3 segundos
    Controller controller;
    public int[] panelIndices;
    private Random randomS = new Random();
    private boolean shouldStopTimer = false;
    private int randomIndex;
    public ImageIcon imageToUpdate;
    private Map<Integer, Integer> panelImageIndexMap;
    private boolean nextLevel = false;
    private boolean button ;
    private  AtomicBoolean isButtonClicked;
    private int life;
    private int totalAttempts;
    private int totalSuccesses;
    private int totalFailures;
    private int score;
    private boolean duplicates;
    
   
        public Model(){
            controller = Controller.getInstance(this);
            panelIndices = new int[]{1, 2, 3};
            panelImageIndexMap = new HashMap<>();
            isButtonClicked = new AtomicBoolean(false);
            life = 0;
            totalAttempts = 1;
            totalSuccesses = 1;
            totalFailures = 1;
            score = 000;
    
    }
    
   

    private static ImageIcon[] imageArray = new ImageIcon[10];
        static {
        // Bloque de inicialización estático para cargar las imágenes al inicio
        try {
            for (int i = 0; i < 10; i++) {
                if (i < 10) {
                    imageArray[i] = new ImageIcon(Model.class.getResource("/view/img/img" + (i + 1) + ".jpeg"));
                } 
            }
        } catch (Exception e) {
            System.err.println("Model: Error al cargar las imágenes: " + e.getMessage());
        }       
    }    
        

    public void initializeGame() {
    // Inicializar el juego llamando al método del nivel uno
    level1();
    
}
    public void level1() {
    System.out.println("Nivel 1: Iniciando...");

    // Actualizar los primeros tres paneles inmediatamente
    startUpdatingPanels();

    // Crear un Timer para actualizar un panel aleatorio cada 2 segundos
    updateTimer = new Timer(2300, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Actualiza un panel aleatorio    
            duplicates = checkForDuplicateImages();
            updateSinglePanelRandomly();
            
               
                    // Verificar si hay paneles coincidentes y si el botón ha sido presionado
                    if (duplicates && button) {
                        handleSuccess();
                        
                    } else if (!duplicates && button) {
                        handleFailure(e);
                    } else if (duplicates && !button) {
                        handleMissedOpportunity(e);
                    }
                    
                    if (shouldEndLevel()) {
                        concludeLevel(e,2);
                    }

                    // Detiene el delayTimer para evitar múltiples instancias
                   // ((Timer)e.getSource()).stop();
            
        }
       
    });
   
   

    updateTimer.setRepeats(true);
    updateTimer.start();
}
//public void level1() {
//    System.out.println("Nivel 1: Iniciando...");
//
//    // Actualizar los primeros tres paneles inmediatamente
//    startUpdatingPanels();
//
//    // Crear un Timer para actualizar un panel aleatorio cada 2 segundos
//    updateTimer = new Timer(2000, new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            // Actualiza un panel aleatorio
//            updateSinglePanelRandomly();
//            checkForDuplicateImages();
//    
//            // Verificar si hay paneles coincidentes y si el botón ha sido presionado
//            if (check && button) {
//                // Si hay coincidencias, se debe establecer shouldStopTimer a verdadero
//                shouldStopTimer = true;
//                totalSuccesses ++;
//                score += 015;
//                
//            } else if ( !check && button) {
//                // Aquí manejas el caso donde no hay duplicados pero el botón ha sido presionado
//                life++;
//                controller.updateLifeIcon(life, false);
//                button = false;
//                isButtonClicked.set(false); 
//                totalFailures++;
//                 if(life == 3){
//                     controller.setEnd(totalAttempts, totalSuccesses, totalFailures);
//                 }
//                
//            } else if (check && !button) {
//                // Tercer caso: el botón no ha sido presionad y hay coinsidencias.
//                // Por ejemplo, podrías querer incrementar el contador de intentos fallidos
//                // o mostrar algún mensaje, etc.
//                totalAttempts++;
//                life++;
//                controller.updateLifeIcon(life, false);
//                
//                isButtonClicked.set(false); 
//                totalFailures++;
//                 if(life == 3){
//                     controller.setEnd(totalAttempts, totalSuccesses, totalFailures);
//                 }
//                               
//                
//            }else  {
//                // Aquí manejas el caso donde no hay duplicados y el botón no ha sido presionado
//                // Puedes hacer algo aquí o simplemente no hacer nada
//              
//                button = false;
//            }
//            // Si shouldStopTimer es verdadero, detener el temporizador
//            if (shouldStopTimer) {
//                ((Timer) e.getSource()).stop();
//                System.out.println("Nivel 1: end...");
//                // Aquí puedes llamar a otro método o realizar acciones adicionales si es necesario
//                new Timer(2000, new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        ((Timer) e.getSource()).stop(); // Detener este temporizador
//                        // Prepararse para el siguiente nivel
//                        resetForNextRound();
//                        level2(); // Comenzar el nivel 2
//                    }
//                }).start(); // Iniciar el temporizador de espera
//            }
//        }
//    });
//
//    // Establecer el Timer para que se repita
//    updateTimer.setRepeats(true);
//
//    // Iniciar el Timer
//    updateTimer.start();
//}
    private boolean shouldEndLevel() {
    return shouldStopTimer;
}
    private void concludeLevel(ActionEvent e, int level) {
    ((Timer) e.getSource()).stop();
    System.out.println("Nivel 1: end...");
    prepareForNextLevel(level);
}
    
    private void handleSuccess() {
        // Lógica para manejar el éxito (cuando hay coincidencia y se presiona el botón)
        
        totalSuccesses++;
        score += 15;
        //updateScore(); // Actualizar la puntuación aquí
        shouldStopTimer = true;
        button = false;
        
    }
    
 private void prepareForNextLevel(int caseNumber) {
    new Timer(1200, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ((Timer) e.getSource()).stop();
            resetForNextRound();
            switch (caseNumber) {
                case 2:
                    level2(2210);
                    break;
                case 3:
                    level3(2200);
                    break;
                case 4:
                    level4(2050);
                    break;
                case 5:
                    level5(2030);
                    break;
                case 6:
                    level6(1990);
                    break;
                case 7:
                    controller.setEnd(totalAttempts, totalSuccesses, totalFailures);
                    break;
                // Add additional cases for more levels
                default:
                    System.out.println("Level " + caseNumber + " not implemented.");
                    break;
            }
        }

       
    }).start();
}

 
    private void handleFailure(ActionEvent e) {
        // Lógica para manejar el fallo (cuando no hay coincidencia y se presiona el botón)
        life++;
        controller.updateLifeIcon(life, false); // Suponiendo que se maneja el ícono de vida aquí
        button = false;
        totalFailures++;
        checkEndCondition(e);
    }
    private void handleMissedOpportunity(ActionEvent e) {
        // Lógica para manejar una oportunidad perdida (cuando hay coincidencia y no se presiona el botón)
        totalAttempts++;
        life++;
        controller.updateLifeIcon(life, false); // Suponiendo que se maneja el ícono de vida aquí
        button = false;
        totalFailures++;
        checkEndCondition(e);
    }

    private void checkEndCondition(ActionEvent e) {
        // Verificar si el juego debe terminar
        if (life == 3) {
             ((Timer) e.getSource()).stop();
            controller.setEnd(totalAttempts, totalSuccesses, totalFailures);
        }
    }

//private void updateScore() {
//    // Actualizar la puntuación aquí
//    score.setText(String.format("%03d", totalSuccesses * 15)); // Suponiendo que score es un JLabel
//}
    public void level2(int time) {
        System.out.println("Nivel 2: Iniciando...");
 // Actualizar los primeros tres paneles inmediatamente
    startUpdatingPanels();

    // Crear un Timer para actualizar un panel aleatorio cada 2 segundos
    updateTimer = new Timer(time, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Actualiza un panel aleatorio    
            duplicates = checkForDuplicateImages();
            updateSinglePanelRandomly();
            
               
                    // Verificar si hay paneles coincidentes y si el botón ha sido presionado
                    if (duplicates && button) {
                        handleSuccess();
                        
                    } else if (!duplicates && button) {
                        handleFailure(e);
                    } else if (duplicates && !button) {
                        handleMissedOpportunity(e);
                    }
                    
                    if (shouldEndLevel()) {
                        concludeLevel(e,3);
                    }

                    // Detiene el delayTimer para evitar múltiples instancias
                   // ((Timer)e.getSource()).stop();
            
        }
       
    });
   
   

    updateTimer.setRepeats(true);
    updateTimer.start();
}
    
    public void level3(int time) {
        System.out.println("Nivel 3: Iniciando...");
 // Actualizar los primeros tres paneles inmediatamente
    startUpdatingPanels();

    // Crear un Timer para actualizar un panel aleatorio cada 2 segundos
    updateTimer = new Timer(time, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Actualiza un panel aleatorio    
            duplicates = checkForDuplicateImages();
            updateSinglePanelRandomly();
            
               
                    // Verificar si hay paneles coincidentes y si el botón ha sido presionado
                    if (duplicates && button) {
                        handleSuccess();
                        
                    } else if (!duplicates && button) {
                        handleFailure(e);
                    } else if (duplicates && !button) {
                        handleMissedOpportunity(e);
                    }
                    
                    if (shouldEndLevel()) {
                        concludeLevel(e,4);
                    }

                    // Detiene el delayTimer para evitar múltiples instancias
                   // ((Timer)e.getSource()).stop();
            
        }
       
    });
   
   

    updateTimer.setRepeats(true);
    updateTimer.start();
}
    
    public void level4(int time) {
        System.out.println("Nivel 4: Iniciando...");
 // Actualizar los primeros tres paneles inmediatamente
    startUpdatingPanels();

    // Crear un Timer para actualizar un panel aleatorio cada 2 segundos
    updateTimer = new Timer(time, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Actualiza un panel aleatorio    
            duplicates = checkForDuplicateImages();
            updateSinglePanelRandomly();
            
               
                    // Verificar si hay paneles coincidentes y si el botón ha sido presionado
                    if (duplicates && button) {
                        handleSuccess();
                        
                    } else if (!duplicates && button) {
                        handleFailure(e);
                    } else if (duplicates && !button) {
                        handleMissedOpportunity(e);
                    }
                    
                    if (shouldEndLevel()) {
                        concludeLevel(e,5);
                    }

                    // Detiene el delayTimer para evitar múltiples instancias
                   // ((Timer)e.getSource()).stop();
            
        }
       
    });
   
   

    updateTimer.setRepeats(true);
    updateTimer.start();
}
    public void level5(int time) {
        System.out.println("Nivel 5: Iniciando...");
 // Actualizar los primeros tres paneles inmediatamente
    startUpdatingPanels();

    // Crear un Timer para actualizar un panel aleatorio cada 2 segundos
    updateTimer = new Timer(time, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Actualiza un panel aleatorio    
            duplicates = checkForDuplicateImages();
            updateSinglePanelRandomly();
            
               
                    // Verificar si hay paneles coincidentes y si el botón ha sido presionado
                    if (duplicates && button) {
                        handleSuccess();
                        
                    } else if (!duplicates && button) {
                        handleFailure(e);
                    } else if (duplicates && !button) {
                        handleMissedOpportunity(e);
                    }
                    
                    if (shouldEndLevel()) {
                        concludeLevel(e,6);
                    }

                    // Detiene el delayTimer para evitar múltiples instancias
                   // ((Timer)e.getSource()).stop();
            
        }
       
    });
   
   

    updateTimer.setRepeats(true);
    updateTimer.start();
}
    public void level6(int time) {
            System.out.println("Nivel 6: Iniciando...");
     // Actualizar los primeros tres paneles inmediatamente
        startUpdatingPanels();

        // Crear un Timer para actualizar un panel aleatorio cada 2 segundos
        updateTimer = new Timer(time, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Actualiza un panel aleatorio    
                duplicates = checkForDuplicateImages();
                updateSinglePanelRandomly();


                        // Verificar si hay paneles coincidentes y si el botón ha sido presionado
                        if (duplicates && button) {
                            handleSuccess();

                        } else if (!duplicates && button) {
                            handleFailure(e);
                        } else if (duplicates && !button) {
                            handleMissedOpportunity(e);
                        }

                        if (shouldEndLevel()) {
                            concludeLevel(e,7);
                        }

                        // Detiene el delayTimer para evitar múltiples instancias
                       // ((Timer)e.getSource()).stop();

            }

        });



        updateTimer.setRepeats(true);
        updateTimer.start();
    }
//public void nivelUno() {
//    System.out.println("Nivel 1: Iniciando...");
//
//    // Actualizar los primeros tres paneles inmediatamente
//    startUpdatingPanels();
//
//    // Crear un Timer para actualizar un panel aleatorio cada 2 segundos
//    Timer updateTimer = new Timer(2000, new ActionListener() {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            // Actualiza un panel aleatorio
//            updateSinglePanelRandomly();
//        }
//    });
//
//    // Establecer el Timer para que se repita
//    updateTimer.setRepeats(true);
//
//    // Iniciar el Timer
//    updateTimer.start();
//}



    
//    private void updatePanelsRandomly(int panelCount) {
//     // Se asume que existe un arreglo llamado imageArray que contiene todas las imágenes posibles
//      imageArray = getImageArray(); // Este método debería retornar el arreglo de imágenes
//
//     // Seleccionar imágenes aleatorias del arreglo
//     ImageIcon[] selectedImages = new ImageIcon[panelCount];
//     Random rand = new Random();
//     for (int i = 0; i < panelCount; i++) {
//         int randomIndex = rand.nextInt(imageArray.length);
//         selectedImages[i] = imageArray[randomIndex];
//     }
//
//        // Actualizar paneles con las imágenes seleccionadas
//        updatePanels(selectedImages);
//    }
    public void setController(Controller controller) {


       }
  

    public void handleUserSelection() {
           // Manejar la selección del usuario y determinar si la acción es correcta.
       }



//    public void resetForNextRound() {
//   // Incrementar la dificultad del juego si es necesario
//   // Esto puede significar aumentar el número de paneles o disminuir el tiempo de respuesta, por ejemplo
//   // Si tu juego se basa en niveles, podrías incrementar el nivel aquí
//   // Generar un nuevo conjunto de índices de paneles basados en el nuevo nivel de dificultad
//           panelIndices = new int[difficultyLevel];
//           for (int i = 0; i < difficultyLevel; i++) {
//           panelIndices[i] = i; // O cualquier otra lógica para definir los índices de los paneles
//           }
//           
//           nextLevel = false;
//           shouldStopTimer = false;
//           panelImageIndexMap.clear();
//       // Limpiar el estado del juego si hay más variables o estados que manejar
//       
//   }
    
    
    public void resetForNextRound() {
        
        
    // Incrementar la dificultad del juego si es necesario
    if (difficultyLevel < 9) {
        difficultyLevel++; // Aumentar la dificultad en 1
    }
    
    // Incrementar la dificultad del juego si es necesario
    // Esto puede significar aumentar el número de paneles o disminuir el tiempo de respuesta, por ejemplo
    // Si tu juego se basa en niveles, podrías incrementar el nivel aquí
    // Generar un nuevo conjunto de índices de paneles basados en el nuevo nivel de dificultad
    panelIndices = new int[difficultyLevel];
    for (int i = 0; i < difficultyLevel; i++) {
        panelIndices[i] = i; // O cualquier otra lógica para definir los índices de los paneles
    }
    duplicates =false;
    nextLevel = false;
    shouldStopTimer = false;
    panelImageIndexMap.clear();
    // Limpiar el estado del juego si hay más variables o estados que manejar
    
    
}

    public ImageIcon getImage(int index) {
               System.out.println("Model: Retornando imagen con índice: " + index);
               if(index >= 0 && index < imageArray.length) {
                   return imageArray[index];
               }
               return null; // O una imagen predeterminada
       }

    public ImageIcon getHeartIcon() {
               return new ImageIcon(getClass().getResource("/view/img/heart.png"));
           }

    public ImageIcon getnullIcon() {
               return new ImageIcon(getClass().getResource("/view/img/1bot.png"));
           }

    public ImageIcon getHeartIconOff() {
               return new ImageIcon(getClass().getResource("/view/img/heartoff.png"));
           }

    public void iincreaseDifficulty() {
           // Incrementar la dificultad del juego.
           difficultyLevel++;
       }

    public ImageIcon getRandomImage(int panelIndex,Set<Integer> usedImageIndices) {
     
        do {
        randomIndex = random.nextInt(imageArray.length);
    } while (usedImageIndices.contains(randomIndex));

    // Agrega el índice al conjunto para que no se reutilice en la misma actualización.
    usedImageIndices.add(randomIndex);

    // Guarda el índice de la imagen en el registro con la clave siendo el índice del panel.
    panelImageIndexMap.put(panelIndex, randomIndex);

    // Imprimir el registro para verificación (opcional).
    System.out.println("Panel " + panelIndex + " asignado con índice de imagen: " + randomIndex);

    // Devuelve la imagen correspondiente al índice aleatorio.
    return imageArray[randomIndex];

       }

    public ImageIcon siglGetRandomImage(int panelIndex) {
       randomIndex = random.nextInt(imageArray.length);

    // Guardar el índice de la imagen en el registro con la clave siendo el índice del panel
    panelImageIndexMap.put(panelIndex, randomIndex);

    // Imprimir el registro para verificación (opcional)
    System.out.println("Panel " + panelIndex + " asignado con índice de imagen: " + randomIndex);

    return imageArray[randomIndex];
       }

    private void startUpdatingPanels() {
           System.out.println("Model: cargando paneles");
           
           controller.controllerupdatePanels(panelIndices);
          
       }
//    private void startUpdateSingPanels() {
//       
//        updateTimer = new Timer(2000, new ActionListener() { // Actualiza cada 2 segundos
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            
//            updateSinglePanelRandomly();   
////            if (checkForMatchingPanels()) {
////                updateTimer.stop(); // Detiene el Timer si dos paneles coinciden
////                // Aquí podrías manejar lo que sucede cuando dos paneles coinciden
////            }
//        }
//        
//    });
//    
//    updateTimer.start(); // Comienza el proceso de actualización
//       }
//
//    private void updatePanels(ImageIcon[] selectedImages) {
//            Asegurarse de que el arreglo de imágenes no sea nulo y que su longitud
//        coincida con la cantidad de índices de paneles.
//       if (selectedImages != null && selectedImages.length == panelIndices.length) {
//            Recorrer el arreglo de índices de paneles
//           for (int i = 0; i < panelIndices.length; i++) {
//                Obtener el índice del panel a actualizar
//               int panelIndex = panelIndices[i];
//                Obtener la imagen seleccionada para este panel
//               ImageIcon image = selectedImages[i];
//                Enviar al controlador la imagen y el índice del panel para actualizar la vista
//               controller.updatePanelImage(panelIndex, image);
//           }
//       } else {
//            Manejar el caso en que las imágenes seleccionadas no coincidan con los paneles
//           System.err.println("Error: El número de imágenes seleccionadas no coincide con el número de paneles a actualizar.");
//       }
//       }

//    private void startSinglePanelUpdateProcess() {
//    updateTimer = new Timer(2000, new ActionListener() { // Actualiza cada 2 segundos
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            updateSinglePanelRandomly();
//
//            if (true) {
//                updateTimer.stop(); // Detiene el Timer si dos paneles coinciden
//                // Aquí podrías manejar lo que sucede cuando dos paneles coinciden
//            }
//        }
//    });
//
//    updateTimer.start(); // Comienza el proceso de actualización
//}


    private void updateSinglePanelRandomly() {
        randomIndex = random.nextInt(panelIndices.length);
        int panelToUpdate = panelIndices[randomIndex];
        System.out.println("Model: cargando panel" + panelToUpdate);
        // Aquí deberías tener un método para actualizar la vista con la nueva imagen en el panel específico
        controller.controllerupdateSigPanels(panelToUpdate);
    }


    public void setButtonClicked(boolean clicked) {
            this.isButtonClicked.set(clicked);
            button = true;
            System.out.println("Model: se cambio estado de button ");
        }
    
    public boolean isButtonClicked() {
        return this.isButtonClicked.get();
    }

    public ImageIcon getImageAtIndex(int index) {
    if (index >= 0 && index < imageArray.length) {
        return imageArray[index];
    } else {
        // Manejo de error: el índice está fuera de rango
        return null;
    }
}
public boolean checkForDuplicateImages() {
    Set<Integer> seenImageIndices = new HashSet<>();

    for (Integer imageIndex : panelImageIndexMap.values()) {
        if (!seenImageIndices.add(imageIndex)) {
            // Si el conjunto ya contenía el índice de la imagen, hemos encontrado un duplicado.
            System.out.println("Se encontró un duplicado para el índice de imagen: " + imageIndex);
            return  true;
        }
    }

    // Si llegamos aquí, no hay imágenes duplicadas.
    return false;
}

    public int getScore() {
      return score;
    }




   }
