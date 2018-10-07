package empathy.tau.domain;

import java.util.List;

public class Interaction {
    public String pageNo;
    public int empathyValue;
    public int sanityValue;
    public String interactionDescription;
    public String optionalJournalEntry;
    public int empathyTreshold;
    public int sanityTreshold;
    public List<String> pagesLocked;
    public String takeItemID;
    public Boolean OneTimeInteractionFlag;
    public String RemoveItemId;
    public boolean PurgeInventoryFlag;
}