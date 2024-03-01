Ajoute la valeur de la crypto au niveau de l'entité marketData 
=> on pourra calculer la performance de la crypto par rapport à la veille 

Pour garantir que la transaction ait une date correspondant à la date la plus proche dans marketdata, vous pouvez suivre ces étapes :

    Recevoir la transaction : Lorsqu'une transaction est initiée par un utilisateur, assurez-vous que les données de la transaction comprennent la date de la transaction.

    Consulter les données de marketdata : Avant de valider la transaction, consultez les données de marketdata pour obtenir le prix le plus proche à la date de la transaction. Vous pouvez utiliser le sink configuré pour consommer les mises à jour de prix ou interroger directement la base de données de marketdata.

    Trouver la date la plus proche : Comparez la date de la transaction avec les dates disponibles dans les données de marketdata pour trouver la date la plus proche. Vous pouvez utiliser des algorithmes de recherche comme la recherche binaire pour trouver la date la plus proche.

    Utiliser le prix correspondant : Une fois que vous avez trouvé la date la plus proche, utilisez le prix correspondant pour cette date dans le calcul de la transaction. Cela garantira que le prix utilisé est le plus récent possible sans dépasser la date de la transaction.

    Valider la transaction : Après avoir calculé le coût de la transaction en utilisant le prix le plus proche disponible dans marketdata, validez la transaction et mettez à jour le portefeuille de l'utilisateur en conséquence.

En suivant ces étapes, vous pouvez vous assurer que chaque transaction utilise le prix le plus précis et le plus à jour disponible dans les données de marketdata, ce qui garantit une gestion précise des portefeuilles des utilisateurs.

calcul de la performance : calculer le cout total (cout au moment de l'achat * quantite)
calculer la valeur actuelle du portefeuille (por chaque crypto multiplier sa quantité par son prix actuel )
calculer le rendement brut : soustrayer le cout total d'achat 