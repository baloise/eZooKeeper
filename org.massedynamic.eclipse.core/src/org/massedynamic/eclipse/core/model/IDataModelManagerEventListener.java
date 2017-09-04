/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.massedynamic.eclipse.core.model;

import java.util.EventListener;

/**
 * Typed data model manager listener interface.
 * 
 * @see DataModel
 * @see DataModelManager#addEventListener(IDataModelManagerEventListener)
 * 
 * @author Mark Masse
 */
public interface IDataModelManagerEventListener<M extends DataModel<M, K, D>, K extends Comparable<K>, D> extends EventListener {

    /**
     * Signifies that the data model manager has been destroyed.
     * 
     * @param event The data model event.
     * @see DataModelManager#destroy()
     */
    public void dataModelManagerDestroyed(DataModelManagerEvent<M, K, D> event);

    /**
     * Signifies that a model has been added to the manager.
     * 
     * @param event The data model event.
     * @see DataModelManager#getModel(Comparable)
     */
    public void dataModelManagerDataModelAdded(DataModelManagerEvent<M, K, D> event);

    /**
     * Signifies that a model has been removed from the manager.
     * 
     * @param event The data model event.
     * @see DataModel#destroy()
     */
    public void dataModelManagerDataModelRemoved(DataModelManagerEvent<M, K, D> event);
}
