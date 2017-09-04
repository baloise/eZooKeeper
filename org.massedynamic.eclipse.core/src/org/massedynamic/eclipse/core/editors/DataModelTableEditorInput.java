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

package org.massedynamic.eclipse.core.editors;

import org.massedynamic.eclipse.core.model.DataModel;
import org.massedynamic.eclipse.core.viewers.IElementType;

/**
 * Abstract base class for table editor inputs representing a group of {@link DataModel}.
 * 
 * @author Mark Masse
 */
public abstract class DataModelTableEditorInput<M extends DataModel<M, ?, ?>> extends BaseEditorInput {

    public DataModelTableEditorInput(String editorId) {
        super(editorId);
    }

    public abstract Object getTableViewerInput();

    public abstract IElementType getTableViewerInputElementType();

    @Override
    public String toString() {
        return getClass().getName() + " [TableViewer Input=" + getTableViewerInput() + "]";
    }

}
